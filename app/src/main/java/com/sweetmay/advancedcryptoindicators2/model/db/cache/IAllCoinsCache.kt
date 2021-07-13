package com.sweetmay.advancedcryptoindicators2.model.db.cache

import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.db.GeneralInfoCoinDb
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IAllCoinsCache {
    fun saveCoins(list: List<GeneralInfoCoinDb>): Completable
    fun deleteCoins(): Completable
    fun getAllCoins(): Single<List<GeneralInfoCoinDb>>
    fun findByName(id: String): Single<List<GeneralInfoCoinDb>>
}