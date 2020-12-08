package com.sweetmay.advancedcryptoindicators2.utils.rsi

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
import io.reactivex.rxjava3.core.Single

interface IRsiEvaluator {
    fun calculateRsi(chartData: ChartData, period: Int = 14): Single<RsiEvaluator.RsiEntity>
}