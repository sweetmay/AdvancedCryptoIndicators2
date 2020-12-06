package com.sweetmay.advancedcryptoindicators2.model.cache

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IFavCoinsCache {
    fun saveFavCoin(coinBase: CoinBase): Completable
    fun getFavCoins(): Single<List<CoinDb>>
    fun deleteFavCoin(coinBase: CoinBase): Completable
}