package com.sweetmay.advancedcryptoindicators2.data.db.cache

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.db.CoinDb
import com.sweetmay.advancedcryptoindicators2.data.repo.ResultWrapper

interface IFavCoinsCache {
    suspend fun saveFavCoin(coinView: CoinView): ResultWrapper<Unit>
    suspend fun getFavCoins(): ResultWrapper<List<CoinDb>>
    suspend fun deleteFavCoin(coinView: CoinView): ResultWrapper<Unit>
}