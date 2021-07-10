//package com.sweetmay.advancedcryptoindicators2.di.modules
//
//import com.sweetmay.advancedcryptoindicators2.App
//import com.sweetmay.advancedcryptoindicators2.di.search.SearchComponentScope
//import com.sweetmay.advancedcryptoindicators2.model.db.cache.IAllCoinsCache
//import com.sweetmay.advancedcryptoindicators2.model.db.cache.room.AllCoinsCache
//import com.sweetmay.advancedcryptoindicators2.model.db.dao.CoinsGeneralInfoDao
//import dagger.Module
//import dagger.Provides
//
//@Module
//class AllCoinsCacheModule {
//
//    @SearchComponentScope
//    @Provides
//    fun dao(app: App): CoinsGeneralInfoDao{
//        return app.generalInfoDao
//    }
//
//    @SearchComponentScope
//    @Provides
//    fun getAllCoinsList(daoFav: CoinsGeneralInfoDao): IAllCoinsCache {
//        return AllCoinsCache(daoFav)
//    }
//}