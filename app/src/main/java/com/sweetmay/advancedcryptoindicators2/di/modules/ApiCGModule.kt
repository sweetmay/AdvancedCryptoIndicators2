package com.sweetmay.advancedcryptoindicators2.di.modules

import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiCGModule {

    @Singleton
    @Provides
    @Named("base_url")
    fun baseUrl(app: App): String{
        return app.BASE_URL_CG
    }

    @Singleton
    @Provides
    fun api(@Named ("base_url") url: String): ApiHolderCoinGecko {
        return ApiHolderCoinGecko(url)
    }
}