package com.sweetmay.advancedcryptoindicators2.model.repo.retrofit

import com.sweetmay.advancedcryptoindicators2.model.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers

class CoinsListRepo(private val apiHolder: ApiHolder, private val cache: IFavCoinsCache) : ICoinsListRepo {

    enum class Currency {
        usd,
        rub
    }

    enum class ListFilter {
        market_cap_desc,
        gecko_desc,
        gecko_asc,
        market_cap_asc,
        volume_asc,
        volume_desc,
        id_asc,
        id_desc
    }


    override fun getCoins(currencyAgainst: String, ids: String, order: String): Single<List<CoinBase>> {
        val apiObservable = apiHolder.dataSource.getCoinsList(currencyAgainst, ids, order).subscribeOn(Schedulers.io())
        val favCacheObservable = cache.getFavCoins()
        return Single.zip(apiObservable, favCacheObservable, BiFunction { t1, t2 ->
            for (coin in t1) {
                for (fav in t2) {
                    if (coin.id == fav.id) {
                        coin.is_favorite = true
                        break
                    }
                }
            }
            return@BiFunction t1
        }).subscribeOn(Schedulers.computation())
    }


}