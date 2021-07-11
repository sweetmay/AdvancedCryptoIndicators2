package com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.api

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.GeneralInfoCoinDb
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CoinDetailed
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DataSourceCoinGecko {
  @GET("api/v3/coins/markets")
  suspend fun getCoinsList(
    @Query("vs_currency") currencyAgainst: String,
    @Query("ids") ids: String = "",
    @Query("order") order: String,
    @Query("per_page") perPage: Int = 100, @Query("page") page: Int
  ): List<CoinBase>

  @GET("api/v3/coins/list")
  suspend fun getCompleteList(): List<GeneralInfoCoinDb>


  @GET("api/v3/coins/{coin}")
  fun getCoinDetailedData(
    @Path("coin") coinName: String,
    @Query("localization") localization: Boolean = false,
    @Query("tickers") tickers: Boolean = false,
    @Query("market_data") marketData: Boolean = true,
    @Query("developer_data") developerData: Boolean = false
  ): CoinDetailed

  @GET("api/v3/coins/{coin}/market_chart")
  fun getMarketChart(
    @Path("coin") id: String,
    @Query("vs_currency") currencyAgainst: String,
    @Query("days") days: String
  ): ChartData
}