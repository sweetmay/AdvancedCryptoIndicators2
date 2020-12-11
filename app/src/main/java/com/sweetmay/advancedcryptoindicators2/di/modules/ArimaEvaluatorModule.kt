package com.sweetmay.advancedcryptoindicators2.di.modules

import com.sweetmay.advancedcryptoindicators2.di.detailed.CoinDetailedSubComponentScope
import com.sweetmay.advancedcryptoindicators2.utils.arima.ArimaEvaluator
import com.sweetmay.advancedcryptoindicators2.utils.arima.IArimaEvaluator
import dagger.Module
import dagger.Provides

@Module
class ArimaEvaluatorModule {

    @CoinDetailedSubComponentScope
    @Provides
    fun arima(): IArimaEvaluator{
        return ArimaEvaluator()
    }
}