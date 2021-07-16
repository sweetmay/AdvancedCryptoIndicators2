package com.sweetmay.advancedcryptoindicators2.presentation.callback

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView

interface BaseListCallBack {
  fun onItemClick(coinView: CoinView)
}