package com.sweetmay.advancedcryptoindicators2.di.modules

import com.sweetmay.advancedcryptoindicators2.di.search.SearchComponentScope
import com.sweetmay.advancedcryptoindicators2.model.db.cache.IAllCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.cache.room.AllCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.dao.CoinsDbDao
import dagger.Module
import dagger.Provides

@Module
class AllCoinsCacheModule {
    @SearchComponentScope
    @Provides
    fun getAllCoinsList(dbDao: CoinsDbDao): IAllCoinsCache {
        return AllCoinsCache(dbDao)
    }

}