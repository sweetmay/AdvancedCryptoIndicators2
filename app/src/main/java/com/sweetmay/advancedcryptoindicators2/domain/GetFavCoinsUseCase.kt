package com.sweetmay.advancedcryptoindicators2.domain

import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinItem
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper
import com.sweetmay.advancedcryptoindicators2.model.settings.ISettings
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.base.MainListViewState
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter

class GetFavCoinsUseCase(
    private val favCoinsCache: IFavCoinsCache,
    private val coinsListRepo: ICoinsListRepo,
    private val settings: ISettings,
    private val converter: Converter
) : IGetFavCoinsUseCase {
  override suspend fun getFavCoins(): MainListViewState<List<CoinView>> {
    val favCoins = favCoinsCache.getFavCoins()

    if (favCoins is ResultWrapper.Error) {
      return MainListViewState.Error(favCoins.errorMsg)
    }

    val fetchedCoins = coinsListRepo.getCoins(
        settings.currencyAgainst,
        converter.convertIdsToString((favCoins as ResultWrapper.Success).value),
        settings.order,
    )

    return when (fetchedCoins) {
      is ResultWrapper.Success -> {
        MainListViewState.Success(fetchedCoins.value.map(CoinItem::toCoinView))
      }
      is ResultWrapper.Error -> {
        MainListViewState.Error(fetchedCoins.errorMsg)
      }
    }
  }
}