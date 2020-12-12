package com.sweetmay.advancedcryptoindicators2.utils.arima

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.workday.insights.timeseries.arima.Arima
import com.workday.insights.timeseries.arima.struct.ArimaParams
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ArimaEvaluator : IArimaEvaluator {

    private val p = 2
    private val d = 1
    private val q = 1
    private val P = 1
    private val D = 1
    private val Q = 0
    private val m = 0

    val priceConverter = Converter()
    private val arimaParams = ArimaParams(p, d, q, P, D, Q, m)

    override fun calculateArima(chartData: ChartData, predictionPeriod: Int): Single<DoubleArray> {
        return Single.fromCallable{
            val priceList = priceConverter.convertChartDataForArima(chartData)
            Arima.forecast_arima(priceList, predictionPeriod, arimaParams).forecast
        }.subscribeOn(Schedulers.computation())
    }

}