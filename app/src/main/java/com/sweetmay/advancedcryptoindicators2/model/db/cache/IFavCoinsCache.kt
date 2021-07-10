package com.sweetmay.advancedcryptoindicators2.model.db.cache

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IFavCoinsCache {
    suspend fun saveFavCoin(coinBase: CoinBase): ResultWrapper<Unit>
    suspend fun getFavCoins(): ResultWrapper<List<CoinDb>>
    suspend fun deleteFavCoin(coinBase: CoinBase): ResultWrapper<Unit>
}