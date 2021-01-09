package com.sweetmay.advancedcryptoindicators2.utils.arima

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
import io.reactivex.rxjava3.core.Single

interface IArimaEvaluator {
    fun calculateArima(chartData: ChartData, predictionPeriod: Int = 30, currentPrice: Float)
    : Single<ArimaEntity>
}