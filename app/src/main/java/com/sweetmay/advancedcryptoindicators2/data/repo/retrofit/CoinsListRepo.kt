package com.sweetmay.advancedcryptoindicators2.data.repo.retrofit

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinItem
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.db.GeneralInfoCoinDb
import com.sweetmay.advancedcryptoindicators2.data.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.data.repo.ResultWrapper
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
  ): ResultWrapper<List<CoinItem>> {
    return try {
      ResultWrapper.Success(
        apiHolderCoinGecko.dataSourceCoinGecko.getCoinsList(
          currencyAgainst,
          ids = ids,
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