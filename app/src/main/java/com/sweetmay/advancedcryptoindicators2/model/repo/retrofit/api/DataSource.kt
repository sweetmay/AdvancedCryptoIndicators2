package com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.api

import com.sweetmay.advancedcryptoindicators2.model.entity.Coin
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataSource {
    @GET("api/v3/coins/markets")
    fun getCoinsList(@Query("vs_currency") currencyAgainst: String
                     , @Query("order") order: String
                     , @Query("per_page") perPage: Int = 250): Single<List<Coin>>
}