package com.sweetmay.advancedcryptoindicators2.utils.arima

import android.animation.ArgbEvaluator
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.exception.ArimaException
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ArimaEvaluator(private val argbEvaluator: ArgbEvaluator, val converter: Converter) : IArimaEvaluator {

    override fun calculateArima(chartData: ChartData, predictionPeriod: Int): Single<ArimaEntity> {
        return Single.fromCallable{
            val arima = ArimaEntity(chartData, predictionPeriod, converter, argbEvaluator)
            if(arima.forecastLast<0){
                throw ArimaException()
            }else {
                arima
            }
        }.subscribeOn(Schedulers.computation())
    }

}