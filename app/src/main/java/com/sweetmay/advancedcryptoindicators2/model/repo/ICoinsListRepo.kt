package com.sweetmay.advancedcryptoindicators2.model.repo

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.GeneralInfoCoinDb
import io.reactivex.rxjava3.core.Single

interface ICoinsListRepo {
    fun getCoins(currencyAgainst: String, ids: String = "",
                    order: String, page: Int = 1): Single<List<CoinBase>>
    fun saveFullList(): Single<List<GeneralInfoCoinDb>>
}