package com.sweetmay.advancedcryptoindicators2.domain.usecase

import com.sweetmay.advancedcryptoindicators2.data.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.data.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.data.repo.ResultWrapper
import com.sweetmay.advancedcryptoindicators2.data.settings.ISettings
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.FavViewState
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter


class GetFavCoinsUseCase(
    private val favCoinsCache: IFavCoinsCache,
    private val coinsListRepo: ICoinsListRepo,
    private val settings: ISettings,
    private val converter: Converter
) : IGetFavCoinsUseCase {
  override suspend fun getFavCoins(): FavViewState<List<CoinView>> {
    val favCoins = favCoinsCache.getFavCoins()

    if (favCoins is ResultWrapper.Error) {
      return FavViewState.Error(favCoins.errorMsg)
    }
    if((favCoins as ResultWrapper.Success).value.isEmpty()){
      return FavViewState.Empty
    }

    val fetchedCoins = coinsListRepo.getCoins(
        settings.currencyAgainst,
        converter.convertIdsToString((favCoins as ResultWrapper.Success).value),
        settings.order,
    )

    return when (fetchedCoins) {
      is ResultWrapper.Success -> {
        FavViewState.Success(fetchedCoins.value.map {
          val result = it.toCoinView()
          result.is_favorite = true
          result
        })
      }
      is ResultWrapper.Error -> {
        FavViewState.Error(fetchedCoins.errorMsg)
      }
    }
  }
}