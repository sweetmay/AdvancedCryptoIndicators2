package com.sweetmay.advancedcryptoindicators2.data.repo.retrofit.api

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.fng.FnGEntity
import retrofit2.http.GET
import retrofit2.http.Query

//Alternative is an api name

interface DataSourceAlternative {

    @GET("fng/")
    suspend fun getFngData(@Query("limit") limit: String = "30"): FnGEntity

}