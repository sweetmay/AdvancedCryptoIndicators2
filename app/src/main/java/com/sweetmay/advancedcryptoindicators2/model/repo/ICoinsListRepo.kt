package com.sweetmay.advancedcryptoindicators2.model.repo

import com.sweetmay.advancedcryptoindicators2.model.entity.Coin
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import io.reactivex.rxjava3.core.Single

interface ICoinsListRepo {
    fun getCoins(currencyAgainst: String = CoinsListRepo.Currency.usd.toString(),
                 order: String = CoinsListRepo.ListFilter.market_cap_desc.toString()): Single<List<Coin>>
}