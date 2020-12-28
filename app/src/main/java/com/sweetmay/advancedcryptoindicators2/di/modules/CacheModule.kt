package com.sweetmay.advancedcryptoindicators2.di.modules

import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.cache.room.FavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.dao.FavCoinsDao
import dagger.Module
import dagger.Provides

@Module
class CacheModule {

    @Provides
    fun dao(app: App): FavCoinsDao {
        return app.favCoinsDao
    }

    @Provides
    fun cache(daoFav: FavCoinsDao): IFavCoinsCache{
        return FavCoinsCache(daoFav)
    }
}