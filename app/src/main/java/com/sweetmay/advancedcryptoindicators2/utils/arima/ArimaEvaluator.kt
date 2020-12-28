package com.sweetmay.advancedcryptoindicators2.utils.arima

import android.animation.ArgbEvaluator
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ArimaEvaluator(val argbEvaluator: ArgbEvaluator, val converter: Converter) : IArimaEvaluator {

    override fun calculateArima(chartData: ChartData, predictionPeriod: Int): Single<ArimaEntity> {
        return Single.fromCallable{
            ArimaEntity(chartData, predictionPeriod, converter, argbEvaluator)
        }.subscribeOn(Schedulers.computation())
    }

}