package com.sweetmay.advancedcryptoindicators2.model.repo

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.GeneralInfoCoinDb
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Job

interface ICoinsListRepo {
    suspend fun getCoins(currencyAgainst: String, ids: String = "",
                    order: String, page: Int = 1): ResultWrapper<List<CoinBase>>
    suspend fun saveFullList(): ResultWrapper<List<GeneralInfoCoinDb>>
}