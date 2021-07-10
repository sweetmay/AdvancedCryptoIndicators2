package com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.api

import com.sweetmay.advancedcryptoindicators2.model.entity.fng.FnGEntity
import retrofit2.http.GET
import retrofit2.http.Query

//Alternative is an api name

interface DataSourceAlternative {

    @GET("fng/")
    suspend fun getFngData(@Query("limit") limit: String = "30"): FnGEntity

}