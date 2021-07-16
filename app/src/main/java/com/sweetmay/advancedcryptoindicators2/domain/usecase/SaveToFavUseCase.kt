package com.sweetmay.advancedcryptoindicators2.domain.usecase

import com.sweetmay.advancedcryptoindicators2.data.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView

class SaveToFavUseCase(private val favCoinsCache: IFavCoinsCache) : ISaveToFavUseCase {
  override suspend fun saveToFav(coinView: CoinView) {
    favCoinsCache.saveFavCoin(coinView)
  }
}