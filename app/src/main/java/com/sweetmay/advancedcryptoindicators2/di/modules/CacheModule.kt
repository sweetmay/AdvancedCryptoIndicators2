package com.sweetmay.advancedcryptoindicators2.di.modules

import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.cache.room.FavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.dao.CoinsDbDao
import dagger.Module
import dagger.Provides

@Module
class CacheModule {

    @Provides
    fun dao(app: App): CoinsDbDao {
        return app.dbDao
    }

    @Provides
    fun cache(dbDao: CoinsDbDao): IFavCoinsCache{
        return FavCoinsCache(dbDao)
    }
}