package com.sweetmay.advancedcryptoindicators2.model.repo.retrofit

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CoinsListRepo(private val apiHolder: ApiHolder): ICoinsListRepo {

    enum class Currency{
        usd,
        rub
    }

    enum class ListFilter{
        market_cap_desc,
        gecko_desc,
        gecko_asc,
        market_cap_asc,
        volume_asc,
        volume_desc,
        id_asc,
        id_desc
    }


    override fun getCoins(currencyAgainst: String, order: String): Single<List<CoinBase>> {
        return apiHolder.dataSource.getCoinsList(currencyAgainst, order).subscribeOn(Schedulers.io())
    }
}