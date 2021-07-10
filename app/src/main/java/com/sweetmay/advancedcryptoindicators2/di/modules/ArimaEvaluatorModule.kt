//package com.sweetmay.advancedcryptoindicators2.di.modules
//
//import android.animation.ArgbEvaluator
//import com.sweetmay.advancedcryptoindicators2.di.detailed.CoinDetailedSubComponentScope
//import com.sweetmay.advancedcryptoindicators2.utils.arima.ArimaEvaluator
//import com.sweetmay.advancedcryptoindicators2.utils.arima.IArimaEvaluator
//import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
//import dagger.Module
//import dagger.Provides
//
//@Module
//class ArimaEvaluatorModule {
//
//    @CoinDetailedSubComponentScope
//    @Provides
//    fun arima(argbEvaluator: ArgbEvaluator, converter: Converter): IArimaEvaluator{
//        return ArimaEvaluator(argbEvaluator, converter)
//    }
//}