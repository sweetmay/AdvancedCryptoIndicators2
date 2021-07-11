package com.sweetmay.advancedcryptoindicators2.model.repo.retrofit

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.GeneralInfoCoinDb
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper
import com.sweetmay.advancedcryptoindicators2.utils.PagingConfig
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko

class CoinsListRepo(
  private val apiHolderCoinGecko: ApiHolderCoinGecko,
  val pagingConfig: PagingConfig
) : ICoinsListRepo {


  override suspend fun getCoins(
    currencyAgainst: String,
    ids: String,
    order: String,
    page: Int
  ): ResultWrapper<List<CoinBase>> {
    return try {
      ResultWrapper.Success(
        apiHolderCoinGecko.dataSourceCoinGecko.getCoinsList(
          currencyAgainst,
          order = order,
          page = page,
          perPage = pagingConfig.perPageLimit
        )
      )
    } catch (e: Throwable) {
      ResultWrapper.Error(e.message ?: "Api Exception")
    }
  }

  override suspend fun saveFullList(): ResultWrapper<List<GeneralInfoCoinDb>> {
    return try {
      ResultWrapper.Success(apiHolderCoinGecko.dataSourceCoinGecko.getCompleteList())
    } catch (e: Throwable) {
      ResultWrapper.Error(e.message ?: "Api Exception")
    }
  }
}