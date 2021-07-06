package com.sweetmay.advancedcryptoindicators2.model.repo.retrofit

import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.GeneralInfoCoinDb
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers

class CoinsListRepo(
  private val apiHolderCoinGecko: ApiHolderCoinGecko,
  private val cache: IFavCoinsCache,
  private val converter: Converter
) : ICoinsListRepo {


  override fun getCoins(
    currencyAgainst: String,
    ids: String,
    order: String,
    page: Int
  ): Single<List<CoinBase>> {
    val apiObservable =
      apiHolderCoinGecko.dataSourceCoinGecko.getCoinsList(currencyAgainst, ids, order, page = page)
        .subscribeOn(Schedulers.io())
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

  override fun saveFullList(): Single<List<GeneralInfoCoinDb>> {
    return apiHolderCoinGecko.dataSourceCoinGecko.getCompleteList().subscribeOn(Schedulers.io())
  }

  override fun getTrending(
    currencyAgainst: String,
    ids: String,
    order: String
  ): Single<List<CoinBase>> {
    val trending = apiHolderCoinGecko.dataSourceCoinGecko.getTrending().subscribeOn(Schedulers.io())
    return trending.flatMap {
      getCoins(currencyAgainst, converter.convertTrendingCoinIdsToString(it), order)
    }
  }
}

