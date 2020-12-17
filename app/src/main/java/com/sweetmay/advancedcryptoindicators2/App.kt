package com.sweetmay.advancedcryptoindicators2

import android.app.Application
import androidx.room.Room
import com.sweetmay.advancedcryptoindicators2.di.app.AppComponent
import com.sweetmay.advancedcryptoindicators2.di.app.DaggerAppComponent
import com.sweetmay.advancedcryptoindicators2.di.detailed.CoinDetailedSubComponent
import com.sweetmay.advancedcryptoindicators2.di.fav.FavSubComponent
import com.sweetmay.advancedcryptoindicators2.di.fng.FnGSubComponent
import com.sweetmay.advancedcryptoindicators2.di.list.ListSubComponent
import com.sweetmay.advancedcryptoindicators2.di.modules.AppModule
import com.sweetmay.advancedcryptoindicators2.model.db.DataBase
import com.sweetmay.advancedcryptoindicators2.model.db.dao.FavCoinsDao

class App: Application() {

    lateinit var dao: FavCoinsDao
    val BASE_URL_CG = "https://api.coingecko.com/"
    val BASE_URL_FNG = "https://api.alternative.me/"
    lateinit var appComponent: AppComponent
    var listSubComponent: ListSubComponent? = null
        private set
    var favSubComponent: FavSubComponent? = null
        private set
    var coinDetailedSubComponent: CoinDetailedSubComponent? = null
        private set
    var fnGSubComponent: FnGSubComponent? = null
        private set


    companion object{
        lateinit var instance: App
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDb()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun initListComponent(): ListSubComponent? {
        listSubComponent = appComponent.listSubComponent()
        return listSubComponent
    }

    fun initFngComponent(): FnGSubComponent?{
        fnGSubComponent = appComponent.fngComponent()
        return fnGSubComponent
    }

    fun initFavComponent(): FavSubComponent? {
        favSubComponent = appComponent.favComponent()
        return favSubComponent
    }

    fun initDetailedComponent(): CoinDetailedSubComponent? {
        coinDetailedSubComponent = appComponent.listSubComponent().coinDetailedSubComponent()
        return coinDetailedSubComponent
    }


    fun releaseDetailedSubComponent(){
        coinDetailedSubComponent = null
    }

    fun releaseFngSubComponent(){
        fnGSubComponent = null
    }

    fun releaseFavComponent(){
        favSubComponent = null
    }

    fun releaseListComponent(){
        listSubComponent = null
    }

    private fun initDb() {
        dao = Room.databaseBuilder(
            applicationContext,
            DataBase::class.java,
            "CoinDb"
        ).build()
                .getDao()
    }
}