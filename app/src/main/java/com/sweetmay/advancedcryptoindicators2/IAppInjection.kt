package com.sweetmay.advancedcryptoindicators2

import com.sweetmay.advancedcryptoindicators2.di.detailed.CoinDetailedSubComponent
import com.sweetmay.advancedcryptoindicators2.di.fav.FavSubComponent
import com.sweetmay.advancedcryptoindicators2.di.fng.FnGSubComponent
import com.sweetmay.advancedcryptoindicators2.di.list.ListSubComponent
import com.sweetmay.advancedcryptoindicators2.di.search.SearchSubComponent

interface IAppInjection {
    fun initListComponent(): ListSubComponent?
    fun initFngComponent(): FnGSubComponent?
    fun initFavComponent(): FavSubComponent?
    fun initSearchComponent(): SearchSubComponent?
    fun initDetailedComponent(): CoinDetailedSubComponent?
    fun releaseDetailedSubComponent()
    fun releaseFngSubComponent()
    fun releaseFavComponent()
    fun releaseListComponent()
    fun releaseSearchComponent()
}