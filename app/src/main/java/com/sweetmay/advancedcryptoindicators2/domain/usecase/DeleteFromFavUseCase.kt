package com.sweetmay.advancedcryptoindicators2.domain.usecase

import com.sweetmay.advancedcryptoindicators2.data.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView

class DeleteFromFavUseCase(private val favCoinsCache: IFavCoinsCache): IDeleteFromFavUseCase {
  override suspend fun deleteFromFav(coinView: CoinView) {
    favCoinsCache.deleteFavCoin(coinView)
  }
}