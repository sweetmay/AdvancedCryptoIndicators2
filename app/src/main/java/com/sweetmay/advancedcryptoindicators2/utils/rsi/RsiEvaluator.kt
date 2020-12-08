package com.sweetmay.advancedcryptoindicators2.utils.rsi

import android.animation.ArgbEvaluator
import android.graphics.Color
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RsiEvaluator(val argbEvaluator: ArgbEvaluator): IRsiEvaluator {

    override fun calculateRsi(chartData: ChartData, period: Int): Single<RsiEntity> {
        return Single.fromCallable {
            val priceList = arrayListOf<Float>()
            for (element in chartData.prices){
                priceList.add(element[1])
            }
            return@fromCallable RsiEntity(100-(100/(1+calculateRS(priceList, period))))
        }.subscribeOn(Schedulers.computation())
    }

    private fun calculateRS(prices: List<Float>, period: Int): Float{
        var AvgGain = 0f
        var AvgLoss = 0f
        var upNum = 0
        var downNum = 0

        for (i in 0 until prices.size - period) {
            if (i == 0) {
                continue
            }
            if (prices[i] > prices[i - 1]) {
                AvgGain += prices[i] - prices[i - 1]
                upNum++
            }
            if (prices[i] < prices[i - 1]) {
                AvgLoss += prices[i - 1] - prices[i]
                downNum++
            }
        }
        if (upNum != 0) {
            AvgGain /= upNum.toFloat()
        }
        if (downNum != 0) {
            AvgLoss /= downNum.toFloat()
        }

        for (i in prices.size - period until prices.size) {
            if (prices[i] > prices[i - 1]) {
                AvgGain = (AvgGain * (period - 1) + prices[i] - prices[i - 1]) / period
            }
            if (prices[i] < prices[i - 1]) {
                AvgLoss = (AvgLoss * (period - 1) + prices[i - 1] - prices[i]) / period
            }
        }
        return AvgGain / AvgLoss
    }

    inner class RsiEntity(val rsi: Float,
                         val possibleEntry: Float = 0f,
                         val possibleTarget: Float = 0f,
                         val stopLoss: Float = 0f){
        fun getRsiColor(): Int{
            return argbEvaluator.evaluate(rsi/100f, Color.GREEN, Color.RED) as Int
        }
    }

}