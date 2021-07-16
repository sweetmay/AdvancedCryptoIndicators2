//package com.sweetmay.advancedcryptoindicators2.presenter
//
//import android.util.Log
//import com.sweetmay.advancedcryptoindicators2.IAppInjection
//import com.sweetmay.advancedcryptoindicators2.data.db.cache.IFavCoinsCache
//import com.sweetmay.advancedcryptoindicators2.domain.model.entity.coin.CoinBase
//import com.sweetmay.advancedcryptoindicators2.domain.model.entity.coin.detailed.CoinDetailed
//import com.sweetmay.advancedcryptoindicators2.data.repo.ICoinDataRepo
//import com.sweetmay.advancedcryptoindicators2.data.settings.ISettings
//import com.sweetmay.advancedcryptoindicators2.utils.arima.IArimaEvaluator
//import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
//import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoaderAsDrawable
//import com.sweetmay.advancedcryptoindicators2.utils.rsi.IRsiEvaluator
//import com.sweetmay.advancedcryptoindicators2.presentation.view.CoinDataView
//import io.reactivex.rxjava3.core.Scheduler
//import moxy.MvpPresenter
//import javax.inject.Inject
//
//class CoinDataFragmentPresenter(val injection: IAppInjection) : MvpPresenter<CoinDataView>() {
//
//    init {
//        injection.initDetailedComponent()?.inject(this)
//    }
//
//    @Inject
//    lateinit var coinDataRepo: ICoinDataRepo
//    @Inject
//    lateinit var scheduler: Scheduler
//    @Inject
//    lateinit var imageLoader: IImageLoaderAsDrawable
//    @Inject
//    lateinit var rsiEvaluator: IRsiEvaluator
//    @Inject
//    lateinit var arimaEvaluator: IArimaEvaluator
//    @Inject
//    lateinit var favCache: IFavCoinsCache
//    @Inject
//    lateinit var converter: Converter
//    @Inject
//    lateinit var settings: ISettings
//
//    fun loadData(coinBase: CoinBase) {
//        viewState.showLoading()
//        viewState.setTitle(coinBase.name)
//        viewState.setFavButton(coinBase)
//        coinDataRepo.getCoin(coinBase).observeOn(scheduler).doAfterSuccess {
//            viewState.hideLoading()
//        }.subscribe ({ coinDetailed ->
//            setPrice(coinDetailed)
//            setChange(coinDetailed)
//            setSentiment(coinDetailed)
//            loadImage(coinDetailed.image.small)
//            loadArimaAndRsi(coinBase)
//        }, {
//            viewState.renderError(it as Exception)
//        })
//    }
//
//    private fun loadArimaAndRsi(coinBase: CoinBase) {
//        loadRsi(coinBase)
//        loadArima(coinBase)
//    }
//
//    private fun setPrice(coinDetailed: CoinDetailed) {
//        viewState.setPrice(
//                converter.convertPrice(
//                        settings.getPriceByPreference(coinDetailed.market_data.current_price)))
//    }
//
//    private fun setSentiment(coinDetailed: CoinDetailed) {
//        val sentiment = coinDetailed.sentiment_votes_up_percentage.toInt()
//        if (sentiment > 0 && sentiment !=100) {
//            viewState.setSentimentView(sentiment)
//        }else{
//            viewState.onSentimentError()
//        }
//    }
//
//    private fun setChange(coinDetailed: CoinDetailed) {
//        val change = settings.getChangeByPreference(coinDetailed.market_data
//                .price_change_percentage_24h_in_currency)
//
//        val convertedChange = converter.convertChange(change)
//        viewState.set24hChange(convertedChange)
//    }
//
//    fun loadArima(coinBase: CoinBase?) {
//         if(coinBase != null) {
//            coinDataRepo.getCoinMarketChartData(coinBase,
//                    settings.currencyAgainst,
//                    settings.arimaTimeFrame).observeOn(scheduler).subscribe({ list ->
//                arimaEvaluator.calculateArima(list, settings.arimaPredictionPeriod, coinBase.current_price)
//                    .observeOn(scheduler)
//                    .subscribe({ arimaEntity ->
//                        viewState.setArima(arimaEntity)
//                    }, {
//                        Log.d("A", it.message.toString())
//                        viewState.showArimaError()
//                    })
//            }, {
//                viewState.renderError(it as Exception)
//            })
//        }
//    }
//
//    fun loadRsi(coinBase: CoinBase?) {
//        if(coinBase!=null){
//            coinDataRepo.getCoinMarketChartData(coinBase,
//                    settings.currencyAgainst,
//                    settings.rsiTimeFrame)
//                    .observeOn(scheduler).subscribe({ chartData ->
//                        rsiEvaluator.calculateRsiEntity(chartData,
//                                settings.rsiPeriod, settings.rsiRR, coinBase.current_price).observeOn(scheduler)
//                                .subscribe({ rsi ->
//                                    viewState.setRsi(rsi)
//                                }, {
//                                    viewState.showRsiError()
//                                })
//                    }, {
//                        viewState.renderError(it as Exception)
//                    })
//        }
//
//    }
//
//    fun deleteFromCache(coinBase: CoinBase) {
//        favCache.deleteFavCoin(coinBase).subscribe()
//    }
//
//    fun saveToCache(coinBase: CoinBase) {
//        favCache.saveFavCoin(coinBase).subscribe()
//    }
//
//    private fun loadImage(url: String) {
//        imageLoader.loadImageAsDrawable(url).observeOn(scheduler).subscribe { img ->
//            viewState.setLogo(img)
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        injection.releaseDetailedSubComponent()
//    }
//
//
//}