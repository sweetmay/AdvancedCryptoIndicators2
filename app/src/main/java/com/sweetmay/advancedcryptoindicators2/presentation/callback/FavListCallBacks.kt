package com.sweetmay.advancedcryptoindicators2.presentation.callback

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView

interface FavListCallBacks: BaseListCallBack {
  fun notifyElementRemoved(pos: Int)
  fun deleteFav(coinView: CoinView)
}