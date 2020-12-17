package com.sweetmay.advancedcryptoindicators2.di.list

import com.sweetmay.advancedcryptoindicators2.di.detailed.CoinDetailedSubComponent
import com.sweetmay.advancedcryptoindicators2.di.modules.AllCoinsCacheModule
import com.sweetmay.advancedcryptoindicators2.di.modules.CacheModule
import com.sweetmay.advancedcryptoindicators2.di.modules.CoinListRepoModule
import com.sweetmay.advancedcryptoindicators2.di.modules.ImageLoaderModule
import com.sweetmay.advancedcryptoindicators2.presenter.ListFragmentPresenter
import dagger.Subcomponent

@ListSubComponentScope
@Subcomponent(modules = [
    CoinListRepoModule::class,
    ImageLoaderModule::class,
    CacheModule::class,
    AllCoinsCacheModule::class
])
interface ListSubComponent {
    fun coinDetailedSubComponent(): CoinDetailedSubComponent
    fun inject(listFragmentPresenter: ListFragmentPresenter)
}