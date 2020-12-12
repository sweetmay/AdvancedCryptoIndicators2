package com.sweetmay.advancedcryptoindicators2.di.fav

import com.sweetmay.advancedcryptoindicators2.di.modules.CacheModule
import com.sweetmay.advancedcryptoindicators2.di.modules.CoinListRepoModule
import com.sweetmay.advancedcryptoindicators2.di.modules.ImageLoaderModule
import com.sweetmay.advancedcryptoindicators2.presenter.FavListPresenter
import dagger.Subcomponent

@FavSubComponentScope
@Subcomponent(modules = [
    CoinListRepoModule::class,
    CacheModule::class,
    ImageLoaderModule::class
])
interface FavSubComponent {
    fun inject(favListPresenter: FavListPresenter)
}