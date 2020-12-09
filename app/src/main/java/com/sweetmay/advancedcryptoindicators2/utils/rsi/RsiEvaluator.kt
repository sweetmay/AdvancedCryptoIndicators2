package com.sweetmay.advancedcryptoindicators2.utils.rsi

import android.animation.ArgbEvaluator
import android.graphics.Color
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RsiEvaluator(val argbEvaluator: ArgbEvaluator) : IRsiEvaluator {
    override fun calculateRsiEntity(chartData: ChartData, period: Int, riskReward: Float): Single<RsiEntity> {
        return Single.fromCallable {
            val priceList = arrayListOf<Float>()
            for (element in chartData.prices) {
                priceList.add(element[1])
            }
            val rsi = RsiEntity(priceList, period, riskReward)
            rsi.indicatorColor = argbEvaluator.evaluate(rsi.signalStrength/100, Color.RED, Color.GREEN) as Int
            return@fromCallable rsi
        }.subscribeOn(Schedulers.computation())
    }
}