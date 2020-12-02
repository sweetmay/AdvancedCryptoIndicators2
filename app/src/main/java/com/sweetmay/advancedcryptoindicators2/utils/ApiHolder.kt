package com.sweetmay.advancedcryptoindicators2.utils

import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.api.DataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiHolder(baseUrl: String) {

    val dataSource: DataSource

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        dataSource = retrofit.create(DataSource::class.java)
    }

}