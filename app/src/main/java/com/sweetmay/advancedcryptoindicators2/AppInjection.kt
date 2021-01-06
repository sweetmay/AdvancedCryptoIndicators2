package com.sweetmay.advancedcryptoindicators2

import com.sweetmay.advancedcryptoindicators2.di.app.AppComponent
import com.sweetmay.advancedcryptoindicators2.di.app.DaggerAppComponent
import com.sweetmay.advancedcryptoindicators2.di.detailed.CoinDetailedSubComponent
import com.sweetmay.advancedcryptoindicators2.di.fav.FavSubComponent
import com.sweetmay.advancedcryptoindicators2.di.fng.FnGSubComponent
import com.sweetmay.advancedcryptoindicators2.di.list.ListSubComponent
import com.sweetmay.advancedcryptoindicators2.di.modules.AppModule
import com.sweetmay.advancedcryptoindicators2.di.search.SearchSubComponent

class AppInjection(val app: App): IAppInjection {

    private val appComponent: AppComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(app))
            .build()

    private var listSubComponent: ListSubComponent? = null
    private var favSubComponent: FavSubComponent? = null
    private var coinDetailedSubComponent: CoinDetailedSubComponent? = null
    private var fnGSubComponent: FnGSubComponent? = null
    private var searchSubComponent: SearchSubComponent? = null


    override fun initListComponent(): ListSubComponent? {
        listSubComponent = appComponent.listSubComponent()
        return listSubComponent
    }

    override fun initFngComponent(): FnGSubComponent? {
        fnGSubComponent = appComponent.fngComponent()
        return fnGSubComponent
    }

    override fun initFavComponent(): FavSubComponent? {
        favSubComponent = appComponent.favComponent()
        return favSubComponent
    }

    override fun initSearchComponent(): SearchSubComponent? {
        searchSubComponent = appComponent.listSubComponent().searchComponent()
        return searchSubComponent
    }

    override fun initDetailedComponent(): CoinDetailedSubComponent? {
        coinDetailedSubComponent = appComponent.listSubComponent().coinDetailedSubComponent()
        return coinDetailedSubComponent
    }

    override fun releaseDetailedSubComponent() {
        coinDetailedSubComponent = null
    }

    override fun releaseFngSubComponent() {
        fnGSubComponent = null
    }

    override fun releaseFavComponent() {
        favSubComponent = null
    }

    override fun releaseListComponent() {
        listSubComponent = null
    }

    override fun releaseSearchComponent() {
        searchSubComponent = null
    }
}