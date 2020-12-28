package com.sweetmay.advancedcryptoindicators2.model.db.cache.room

import com.sweetmay.advancedcryptoindicators2.model.db.cache.IAllCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.dao.CoinsGeneralInfoDao
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.GeneralInfoCoinDb
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class AllCoinsCache(private val daoFav: CoinsGeneralInfoDao): IAllCoinsCache {

    override fun saveCoins(list: List<GeneralInfoCoinDb>): Completable {
        return Completable.fromCallable {
            daoFav.insertAll(list)
        }.subscribeOn(Schedulers.io())
    }

    override fun deleteCoins(): Completable {
        return Completable.fromCallable {
            daoFav.deleteAll()
        }.subscribeOn(Schedulers.io())
    }

    override fun getAllCoins(): Single<List<GeneralInfoCoinDb>> {
        return Single.fromCallable {
            daoFav.getFullList()
        }.subscribeOn(Schedulers.io())
    }

    override fun findByName(id: String): Single<List<GeneralInfoCoinDb>> {
        return Single.fromCallable {
            daoFav.fundByName(id)
        }.subscribeOn(Schedulers.io())
    }
}