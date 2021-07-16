package com.sweetmay.advancedcryptoindicators2.domain.usecase

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView

interface ISaveToFavUseCase {
  suspend fun saveToFav(coinView: CoinView)
}