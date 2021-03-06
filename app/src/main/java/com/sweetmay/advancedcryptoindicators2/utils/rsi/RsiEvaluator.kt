package com.sweetmay.advancedcryptoindicators2.utils.rsi

import android.animation.ArgbEvaluator
import android.graphics.Color
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RsiEvaluator(private val argbEvaluator: ArgbEvaluator) : IRsiEvaluator {
    override fun calculateRsiEntity(chartData: ChartData, period: Int, rr: Int, currentPrice: Float): Single<RsiEntity> {
        return Single.fromCallable {
            val priceList = Converter().convertChartDataForRsi(chartData)
            priceList[0] = currentPrice
            val rsi = RsiEntity(priceList, period, rr)
            rsi.indicatorColor = argbEvaluator
                .evaluate(rsi.signalStrength / 100, Color.RED, Color.GREEN) as Int
            return@fromCallable rsi
        }.subscribeOn(Schedulers.computation())
    }
}