package com.sweetmay.advancedcryptoindicators2.di.modules

import com.sweetmay.advancedcryptoindicators2.model.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import dagger.Module
import dagger.Provides

@Module
class CoinListRepoModule {

    @Provides
    fun repo(apiHolder: ApiHolder, cache: IFavCoinsCache): ICoinsListRepo{
        return CoinsListRepo(apiHolder, cache)
    }
}