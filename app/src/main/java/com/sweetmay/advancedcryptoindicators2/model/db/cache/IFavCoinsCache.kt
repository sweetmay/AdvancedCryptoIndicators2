package com.sweetmay.advancedcryptoindicators2.model.db.cache

import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.db.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper

interface IFavCoinsCache {
    suspend fun saveFavCoin(coinView: CoinView): ResultWrapper<Unit>
    suspend fun getFavCoins(): ResultWrapper<List<CoinDb>>
    suspend fun deleteFavCoin(coinView: CoinView): ResultWrapper<Unit>
}