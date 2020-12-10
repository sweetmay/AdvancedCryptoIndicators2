package com.sweetmay.advancedcryptoindicators2.di.detailed

import com.sweetmay.advancedcryptoindicators2.di.modules.CoinDataRepoModule
import com.sweetmay.advancedcryptoindicators2.di.modules.ImageLoaderModule
import com.sweetmay.advancedcryptoindicators2.di.modules.RsiEvaluatorModule
import com.sweetmay.advancedcryptoindicators2.presenter.CoinDataFragmentPresenter
import dagger.Subcomponent

@CoinDetailedSubComponentScope
@Subcomponent(modules = [
    CoinDataRepoModule::class,
    ImageLoaderModule::class,
    RsiEvaluatorModule::class
])
interface CoinDetailedSubComponent {
    fun inject(coinDataFragmentPresenter: CoinDataFragmentPresenter)
}