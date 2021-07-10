//package com.sweetmay.advancedcryptoindicators2.model.repo.retrofit
//
//import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
//import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData
//import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CoinDetailed
//import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinDataRepo
//import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko
//import io.reactivex.rxjava3.core.Single
//import io.reactivex.rxjava3.schedulers.Schedulers
//
//class CoinDataRepo(private val apiHolderCoinGecko: ApiHolderCoinGecko) : ICoinDataRepo {
//
//    override fun getCoin(coinBase: CoinBase): Single<CoinDetailed> {
//        return apiHolderCoinGecko.dataSourceCoinGecko.getCoinDetailedData(coinBase.id).subscribeOn(Schedulers.io())
//    }
//
//    override fun getCoinMarketChartData(coinBase: CoinBase, currencyAgainst: String, period: String): Single<ChartData> {
//        return apiHolderCoinGecko.dataSourceCoinGecko.getMarketChart(coinBase.id, currencyAgainst, period).subscribeOn(Schedulers.io())
//    }
//}