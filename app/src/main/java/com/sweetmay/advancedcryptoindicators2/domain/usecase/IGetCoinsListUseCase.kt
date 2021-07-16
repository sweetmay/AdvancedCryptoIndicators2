package com.sweetmay.advancedcryptoindicators2.domain.usecase

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.MainListViewState
import com.sweetmay.advancedcryptoindicators2.utils.PagingState

interface IGetCoinsListUseCase {
  suspend fun getCoins(pagingState: PagingState): MainListViewState<List<CoinView>>
}