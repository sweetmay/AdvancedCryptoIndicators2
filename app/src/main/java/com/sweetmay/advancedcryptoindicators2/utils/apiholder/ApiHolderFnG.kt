package com.sweetmay.advancedcryptoindicators2.utils.apiholder

import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.api.DataSourceAlternative
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiHolderFnG(baseUrl: String) {

    val dataSourceAlternative: DataSourceAlternative

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        dataSourceAlternative = retrofit.create(DataSourceAlternative::class.java)
    }

}