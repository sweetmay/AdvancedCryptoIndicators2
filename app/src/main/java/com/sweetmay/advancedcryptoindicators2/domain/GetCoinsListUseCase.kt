package com.sweetmay.advancedcryptoindicators2.domain

import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinItem
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.db.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.ViewState
import com.sweetmay.advancedcryptoindicators2.utils.PagingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

class GetCoinsListUseCase(
  private val coinsRepo: ICoinsListRepo,
  private val favCoinsRepo: IFavCoinsCache,
  private val coroutineScope: CoroutineScope,
  private val currencyAgainst: String = "usd",
  private val order: String = "market_cap_desc",
) : IGetCoinsListUseCase {

  override suspend fun getCoins(pagingState: PagingState): ViewState<List<CoinView>> {
    val coins = withContext(coroutineScope.coroutineContext) {
      coinsRepo.getCoins(currencyAgainst, order = order, page = pagingState.page)
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

  private fun matchFavsWithCoins(
    coins: List<CoinItem>,
    favs: List<CoinDb>
  ): ViewState<List<CoinView>> {
    val resultCoinsView = arrayListOf<CoinView>()
    for (coin in coins) {

      val coinToAdd = coin.toCoinView(coin)
      resultCoinsView.add(coinToAdd)

      for (fav in favs) {
        if (coin.id == fav.id) {
          coinToAdd.is_favorite = true
          break
        }
      }

    }
    return ViewState.Success(resultCoinsView)
  }
}