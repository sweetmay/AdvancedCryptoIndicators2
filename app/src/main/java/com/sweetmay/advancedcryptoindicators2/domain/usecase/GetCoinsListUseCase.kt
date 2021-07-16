package com.sweetmay.advancedcryptoindicators2.domain.usecase

import com.sweetmay.advancedcryptoindicators2.data.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinItem
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.db.CoinDb
import com.sweetmay.advancedcryptoindicators2.data.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.data.repo.ResultWrapper
import com.sweetmay.advancedcryptoindicators2.data.settings.ISettings
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.MainListViewState
import com.sweetmay.advancedcryptoindicators2.utils.PagingState
import kotlinx.coroutines.*

class GetCoinsListUseCase(
    private val coinsRepo: ICoinsListRepo,
    private val favCoinsRepo: IFavCoinsCache,
    private val coroutineScope: CoroutineScope,
    private val settings: ISettings
) : IGetCoinsListUseCase {

  override suspend fun getCoins(pagingState: PagingState): MainListViewState<List<CoinView>> {
    val coins = coroutineScope.async {
      coinsRepo.getCoins(settings.currencyAgainst, order =  settings.order, page = pagingState.page)
    }

    val favs = coroutineScope.async {
      favCoinsRepo.getFavCoins()
    }

    val resultCoins = coins.await()
    val resultFavs = favs.await()

    if (resultCoins is ResultWrapper.Error) {
      return MainListViewState.Error(resultCoins.errorMsg)
    }

    if (resultFavs is ResultWrapper.Error) {
      return MainListViewState.Error(resultFavs.errorMsg)
    }

    return matchFavsWithCoins(
        (resultCoins as ResultWrapper.Success).value,
        (resultFavs as ResultWrapper.Success).value)
  }

  private fun matchFavsWithCoins(
      coins: List<CoinItem>,
      favs: List<CoinDb>
  ): MainListViewState<List<CoinView>> {

    val resultCoinsView = arrayListOf<CoinView>()
    for (coin in coins) {

      val coinToAdd = coin.toCoinView()
      resultCoinsView.add(coinToAdd)

      for (fav in favs) {
        if (coin.id == fav.id) {
          coinToAdd.is_favorite = true
          break
        }
      }

    }
    return MainListViewState.Success(resultCoinsView)
  }
}