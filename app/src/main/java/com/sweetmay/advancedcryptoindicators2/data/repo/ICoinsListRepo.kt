package com.sweetmay.advancedcryptoindicators2.data.repo

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinItem
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.db.GeneralInfoCoinDb

interface ICoinsListRepo {
    suspend fun getCoins(currencyAgainst: String, ids: String = "",
                    order: String, page: Int = 1): ResultWrapper<List<CoinItem>>
    suspend fun saveFullList(): ResultWrapper<List<GeneralInfoCoinDb>>
}