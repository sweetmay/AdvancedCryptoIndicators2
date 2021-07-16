package com.sweetmay.advancedcryptoindicators2.domain.usecase

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.FavViewState

interface IGetFavCoinsUseCase {
  suspend fun getFavCoins(): FavViewState<List<CoinView>>
}