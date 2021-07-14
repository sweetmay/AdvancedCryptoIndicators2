package com.sweetmay.advancedcryptoindicators2.domain

import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinView

interface ISaveToFavUseCase {
  suspend fun saveToFav(coinView: CoinView)
}