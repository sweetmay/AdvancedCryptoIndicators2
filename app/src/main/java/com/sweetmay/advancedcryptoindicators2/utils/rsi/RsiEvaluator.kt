package com.sweetmay.advancedcryptoindicators2.utils.rsi

import android.animation.ArgbEvaluator
import android.graphics.Color
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RsiEvaluator(val argbEvaluator: ArgbEvaluator) : IRsiEvaluator {
    override fun calculateRsiEntity(chartData: ChartData, period: Int): Single<RsiEntity> {
        return Single.fromCallable {
            val priceList = Converter().convertChartDataForRsi(chartData)
            val rsi = RsiEntity(priceList, period)
            rsi.indicatorColor = argbEvaluator.evaluate(rsi.signalStrength/40, Color.RED, Color.GREEN) as Int
            return@fromCallable rsi
        }.subscribeOn(Schedulers.computation())
    }
}