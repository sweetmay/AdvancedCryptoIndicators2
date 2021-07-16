package com.sweetmay.advancedcryptoindicators2.utils.apiholder

import com.sweetmay.advancedcryptoindicators2.data.repo.retrofit.api.DataSourceAlternative
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHolderFnG(baseUrl: String) {

    val dataSourceAlternative: DataSourceAlternative

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        dataSourceAlternative = retrofit.create(DataSourceAlternative::class.java)
    }

}