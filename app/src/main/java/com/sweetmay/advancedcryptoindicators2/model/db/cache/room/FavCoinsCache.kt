package com.sweetmay.advancedcryptoindicators2.model.db.cache.room

import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.dao.CoinsDbDao
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class FavCoinsCache(private val dbDao: CoinsDbDao): IFavCoinsCache{

    override fun saveFavCoin(coinBase: CoinBase): Completable {
        return Completable.fromCallable {
            dbDao.insert(convert(coinBase))
        }.subscribeOn(Schedulers.io())
    }

    override fun getFavCoins(): Single<List<CoinDb>> {
        return Single.fromCallable{
            dbDao.getAll()
        }.subscribeOn(Schedulers.io())
    }

    override fun deleteFavCoin(coinBase: CoinBase): Completable {
        return Completable.fromCallable {
            dbDao.delete(convert(coinBase))
        }.subscribeOn(Schedulers.io())
    }

    private fun convert(coinBase: CoinBase): CoinDb{
        return CoinDb(coinBase.id, coinBase.name)
    }


}