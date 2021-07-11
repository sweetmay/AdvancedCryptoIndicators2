package com.sweetmay.advancedcryptoindicators2.domain

import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.ViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

class GetCoinsListUseCase(
  private val coinsRepo: ICoinsListRepo,
  private val favCoinsRepo: IFavCoinsCache,
  private val coroutineScope: CoroutineScope,
  private val currencyAgainst: String = "usd",
  private val order: String = "market_cap_desc",
  private var page: Int = 1
) : IGetCoinsListUseCase {

  override suspend fun getCoins(): ViewState<List<CoinBase>> {
    val coins = withContext(coroutineScope.coroutineContext) {
      coinsRepo.getCoins(currencyAgainst, order = order, page = page)
    }

    val favs = withContext(coroutineScope.coroutineContext) {
      favCoinsRepo.getFavCoins()
    }

    if (coins is ResultWrapper.Error) {
      return ViewState.Error(coins.errorMsg)
    }

    if (favs is ResultWrapper.Error) {
      return ViewState.Error(favs.errorMsg)
    }

    return matchFavsWithCoins(
      (coins as ResultWrapper.Success).value,
      (favs as ResultWrapper.Success).value
    )
  }

  override suspend fun loadMore(pageToLoad: Int): ViewState<List<CoinBase>> {
    page = pageToLoad
    return getCoins()
  }


  private fun matchFavsWithCoins(
    coins: List<CoinBase>,
    favs: List<CoinDb>
  ): ViewState<List<CoinBase>> {
    for (coin in coins) {
      for (fav in favs) {
        if (coin.id == fav.id) {
          coin.is_favorite = true
          break
        }
      }
    }
    return ViewState.Success(coins)
  }
}