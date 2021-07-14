package com.sweetmay.advancedcryptoindicators2.domain

import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.cache.room.FavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinView

class DeleteFromFavUseCase(private val favCoinsCache: IFavCoinsCache): IDeleteFromFavUseCase {
  override suspend fun deleteFromFav(coinView: CoinView) {
    favCoinsCache.deleteFavCoin(coinView)
  }
}