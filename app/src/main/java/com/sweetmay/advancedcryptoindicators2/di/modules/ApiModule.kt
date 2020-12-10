package com.sweetmay.advancedcryptoindicators2.di.modules

import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    @Named("base_url")
    fun baseUrl(app: App): String{
        return app.BASE_URL
    }

    @Singleton
    @Provides
    fun api(@Named ("base_url") url: String): ApiHolder {
        return ApiHolder(url)
    }
}