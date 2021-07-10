//package com.sweetmay.advancedcryptoindicators2.di.detailed
//
//import com.sweetmay.advancedcryptoindicators2.di.modules.ArimaEvaluatorModule
//import com.sweetmay.advancedcryptoindicators2.di.modules.CoinDataRepoModule
//import com.sweetmay.advancedcryptoindicators2.di.modules.ImageLoaderModule
//import com.sweetmay.advancedcryptoindicators2.di.modules.RsiEvaluatorModule
//import dagger.Subcomponent
//
//@CoinDetailedSubComponentScope
//@Subcomponent(modules = [
//    CoinDataRepoModule::class,
//    ImageLoaderModule::class,
//    RsiEvaluatorModule::class,
//    ArimaEvaluatorModule::class
//])
//interface CoinDetailedSubComponent {
//    fun inject(coinDataFragmentPresenter: CoinDataFragmentPresenter)
//    fun inject(coinDataFragment: CoinDataFragment)
//}