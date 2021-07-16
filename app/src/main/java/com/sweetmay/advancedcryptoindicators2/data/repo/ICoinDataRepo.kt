package com.sweetmay.advancedcryptoindicators2.data.repo

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.chart.ChartData
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.detailed.CoinDetailed
import io.reactivex.rxjava3.core.Single

interface ICoinDataRepo {
    fun getCoin(coinView: CoinView): Single<CoinDetailed>
    fun getCoinMarketChartData(coinView: CoinView,
                               currencyAgainst: String,
                               period: String = "1"): Single<ChartData>
}