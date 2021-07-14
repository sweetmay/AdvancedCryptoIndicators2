package com.sweetmay.advancedcryptoindicators2.domain

import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.base.MainListViewState

interface IGetFavCoinsUseCase {
  suspend fun getFavCoins(): MainListViewState<List<CoinView>>
}