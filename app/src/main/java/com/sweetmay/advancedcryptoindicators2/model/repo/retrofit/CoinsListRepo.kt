package com.sweetmay.advancedcryptoindicators2.model.repo.retrofit

import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.GeneralInfoCoinDb
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko
import kotlinx.coroutines.*

class CoinsListRepo(
  private val apiHolderCoinGecko: ApiHolderCoinGecko,
) : ICoinsListRepo {


  override suspend fun getCoins(
    currencyAgainst: String,
    ids: String,
    order: String,
    page: Int
  ): ResultWrapper<List<CoinBase>> {
    return try{
      ResultWrapper.Success(apiHolderCoinGecko.dataSourceCoinGecko.getCoinsList(currencyAgainst, order = order, page = page))
    }catch (e: Throwable){
      ResultWrapper.Error(e.message ?: "Api Exception")
    }
  }

  override suspend fun saveFullList(): ResultWrapper<List<GeneralInfoCoinDb>> {
    return try{
      ResultWrapper.Success(apiHolderCoinGecko.dataSourceCoinGecko.getCompleteList())
    }catch (e: Throwable){
      ResultWrapper.Error(e.message ?: "Api Exception")
    }
  }
}

//        return Single.zip(apiObservable, favCacheObservable, BiFunction { t1, t2 ->
//            for (coin in t1) {
//                for (fav in t2) {
//                    if (coin.id == fav.id) {
//                        coin.is_favorite = true
//                        break
//                    }
//                }
//            }
//            return@BiFunction t1
//        }).subscribeOn(Schedulers.computation())