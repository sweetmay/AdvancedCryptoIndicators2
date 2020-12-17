package com.sweetmay.advancedcryptoindicators2.di.fng

import com.sweetmay.advancedcryptoindicators2.di.modules.ApiFnGModule
import com.sweetmay.advancedcryptoindicators2.di.modules.FnGRepoModule
import com.sweetmay.advancedcryptoindicators2.presenter.FearGreedFragmentPresenter
import dagger.Subcomponent

@FnGSubComponentScope
@Subcomponent(modules =[
    ApiFnGModule::class,
    FnGRepoModule::class
])
interface FnGSubComponent{
    fun inject(fearGreedFragmentPresenter: FearGreedFragmentPresenter)
}