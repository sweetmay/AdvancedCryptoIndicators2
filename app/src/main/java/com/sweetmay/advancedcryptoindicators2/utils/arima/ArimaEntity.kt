package com.sweetmay.advancedcryptoindicators2.utils.arima

import android.animation.ArgbEvaluator
import android.graphics.Color
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.workday.insights.timeseries.arima.Arima
import com.workday.insights.timeseries.arima.struct.ArimaParams

class ArimaEntity(chartData: ChartData, period: Int, priceConverter: Converter, argbEvaluator: ArgbEvaluator) {

    private val p = 3
    private val d = 0
    private val q = 3
    private val P = 0
    private val D = 0
    private val Q = 0
    private val m = 0

    private val arimaParams = ArimaParams(p, d, q, P, D, Q, m)
    val forecastArray: DoubleArray
    val forecastLast: Double

    val priceList = priceConverter.convertChartDataForArima(chartData)
    private val currentPrice = priceList[priceList.size-1]
    var isPositive: Boolean = false
    var indicatorColor = 0

    init{
        forecastArray = Arima.forecast_arima(priceList, period, arimaParams).forecast
        forecastLast = forecastArray.last()
        isPositive = forecastArray.last()>=currentPrice

        indicatorColor =
                if(isPositive){
            argbEvaluator.evaluate(((currentPrice/forecastLast-1)*-1).toFloat(),
                    Color.RED, Color.GREEN) as Int
        }else {
            argbEvaluator.evaluate(((forecastLast/currentPrice-1)*-1).toFloat(),
                    Color.RED, Color.GREEN) as Int
        }
    }
}