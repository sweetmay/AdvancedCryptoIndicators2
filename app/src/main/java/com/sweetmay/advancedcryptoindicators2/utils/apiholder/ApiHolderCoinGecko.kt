package com.sweetmay.advancedcryptoindicators2.utils.apiholder

import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.api.DataSourceCoinGecko
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiHolderCoinGecko(baseUrl: String) {

    val dataSourceCoinGecko: DataSourceCoinGecko

    init {

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        dataSourceCoinGecko = retrofit.create(DataSourceCoinGecko::class.java)
    }

}