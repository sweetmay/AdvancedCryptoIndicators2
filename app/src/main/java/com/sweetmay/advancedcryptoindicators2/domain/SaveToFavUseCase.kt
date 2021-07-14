package com.sweetmay.advancedcryptoindicators2.domain

import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinView

class SaveToFavUseCase(private val favCoinsCache: IFavCoinsCache) : ISaveToFavUseCase {
  override suspend fun saveToFav(coinView: CoinView) {
    favCoinsCache.saveFavCoin(coinView)
  }
}