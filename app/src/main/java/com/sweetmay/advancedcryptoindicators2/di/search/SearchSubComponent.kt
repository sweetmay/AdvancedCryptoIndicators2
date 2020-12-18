package com.sweetmay.advancedcryptoindicators2.di.search

import com.sweetmay.advancedcryptoindicators2.di.modules.AllCoinsCacheModule
import com.sweetmay.advancedcryptoindicators2.presenter.SearchFragmentPresenter
import dagger.Subcomponent

@SearchComponentScope
@Subcomponent(modules = [
    AllCoinsCacheModule::class,
])
interface SearchSubComponent {
    fun inject(searchFragmentPresenter: SearchFragmentPresenter)
}