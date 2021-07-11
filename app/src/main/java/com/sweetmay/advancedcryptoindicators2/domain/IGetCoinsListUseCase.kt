package com.sweetmay.advancedcryptoindicators2.domain

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.ViewState

interface IGetCoinsListUseCase {
  suspend fun getCoins(): ViewState<List<CoinBase>>
  suspend fun loadMore(pageToLoad: Int): ViewState<List<CoinBase>>
}