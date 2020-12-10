package com.sweetmay.advancedcryptoindicators2.di.modules

import android.animation.ArgbEvaluator
import com.sweetmay.advancedcryptoindicators2.di.detailed.CoinDetailedSubComponentScope
import com.sweetmay.advancedcryptoindicators2.utils.rsi.IRsiEvaluator
import com.sweetmay.advancedcryptoindicators2.utils.rsi.RsiEvaluator
import dagger.Module
import dagger.Provides

@Module
class RsiEvaluatorModule {

    @CoinDetailedSubComponentScope
    @Provides
    fun argbEval(): ArgbEvaluator {
        return ArgbEvaluator()
    }

    @CoinDetailedSubComponentScope
    @Provides
    fun rsiEvaluator(argbEvaluator: ArgbEvaluator): IRsiEvaluator{
        return RsiEvaluator(argbEvaluator)
    }
}