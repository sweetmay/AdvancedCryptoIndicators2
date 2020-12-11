package com.sweetmay.advancedcryptoindicators2.model.repo.retrofit

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CoinDetailed
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinDataRepo
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CoinDataRepo(val apiHolder: ApiHolder) : ICoinDataRepo {

    override fun getCoin(coinBase: CoinBase): Single<CoinDetailed> {
        return apiHolder.dataSource.getCoinDetailedData(coinBase.id).subscribeOn(Schedulers.io())
    }

    override fun getCoinMarketChartData(coinBase: CoinBase, currencyAgainst: String, period: String): Single<ChartData> {
        return apiHolder.dataSource.getMarketChart(coinBase.id, currencyAgainst, period).subscribeOn(Schedulers.io())
    }
}