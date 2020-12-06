package com.sweetmay.advancedcryptoindicators2.model.cache.room

import com.sweetmay.advancedcryptoindicators2.model.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.dao.FavCoinsDao
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class FavCoinsCache(private val dao: FavCoinsDao): IFavCoinsCache {

    override fun saveFavCoin(coinBase: CoinBase): Completable {
        return Completable.fromCallable {
            dao.insert(convert(coinBase))
        }.subscribeOn(Schedulers.io())
    }

    override fun getFavCoins(): Single<List<CoinDb>> {
        return Single.fromCallable{
            dao.getAll()
        }.subscribeOn(Schedulers.io())
    }

    override fun deleteFavCoin(coinBase: CoinBase): Completable {
        return Completable.fromCallable {
            dao.delete(convert(coinBase))
        }.subscribeOn(Schedulers.io())
    }

    private fun convert(coinBase: CoinBase): CoinDb{
        return CoinDb(coinBase.id)
    }

}