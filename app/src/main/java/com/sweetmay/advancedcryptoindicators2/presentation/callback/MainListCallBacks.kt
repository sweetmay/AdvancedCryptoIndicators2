package com.sweetmay.advancedcryptoindicators2.presentation.callback

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView

interface MainListCallBacks: BaseListCallBack {
  fun saveFav(coinView: CoinView)
  fun deleteFav(coinView: CoinView)
  fun scrollToLastPos()
  fun updatePagingState(firstItem: Int, lastItem: Int)
}