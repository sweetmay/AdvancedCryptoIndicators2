package com.sweetmay.advancedcryptoindicators2.di.modules

import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko
import dagger.Module
import dagger.Provides

@Module
class CoinListRepoModule {

    @Provides
    fun repo(apiHolderCoinGecko: ApiHolderCoinGecko, cache: IFavCoinsCache): ICoinsListRepo{
        return CoinsListRepo(apiHolderCoinGecko, cache)
    }
}