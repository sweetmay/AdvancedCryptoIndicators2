package com.sweetmay.advancedcryptoindicators2.presenter

import com.sweetmay.advancedcryptoindicators2.IAppInjection
import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CoinDetailed
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinDataRepo
import com.sweetmay.advancedcryptoindicators2.model.settings.ISettings
import com.sweetmay.advancedcryptoindicators2.utils.arima.IArimaEvaluator
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoaderAsDrawable
import com.sweetmay.advancedcryptoindicators2.utils.rsi.IRsiEvaluator
import com.sweetmay.advancedcryptoindicators2.utils.rsi.RsiEntity
import com.sweetmay.advancedcryptoindicators2.view.CoinDataView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import moxy.MvpPresenter
import javax.inject.Inject

class CoinDataFragmentPresenter(val injection: IAppInjection) : MvpPresenter<CoinDataView>() {

    init {
        injection.initDetailedComponent()?.inject(this)
    }

    @Inject
    lateinit var coinDataRepo: ICoinDataRepo
    @Inject
    lateinit var scheduler: Scheduler
    @Inject
    lateinit var imageLoader: IImageLoaderAsDrawable
    @Inject
    lateinit var rsiEvaluator: IRsiEvaluator
    @Inject
    lateinit var arimaEvaluator: IArimaEvaluator
    @Inject
    lateinit var favCache: IFavCoinsCache
    @Inject
    lateinit var converter: Converter
    @Inject
    lateinit var settings: ISettings

    fun loadData(coinBase: CoinBase) {
        viewState.showLoading()
        viewState.setTitle(coinBase.name)
        viewState.setFavButton(coinBase)
        coinDataRepo.getCoin(coinBase).observeOn(scheduler).subscribe ({ coinDetailed ->
            viewState.setPrice(
                    converter.convertPrice(
                            settings.getPriceByPreference(coinDetailed.market_data.current_price)))

            setChange(coinDetailed)
            loadImage(coinDetailed.image.small)
        }, {
            viewState.renderError(it as Exception)
        })
        loadAllData(coinBase)
    }

    private fun setChange(coinDetailed: CoinDetailed) {
        val change = coinDetailed.market_data.price_change_percentage_24h_in_currency.usd
        val convertedChange = converter.convertChange(change)
        viewState.set24hChange(convertedChange)
    }

    private fun loadAllData(coinBase: CoinBase) {
        val rsiChartObservable = coinDataRepo
                .getCoinMarketChartData(coinBase).observeOn(scheduler)
        val arimaChartObservable = coinDataRepo
                .getCoinMarketChartData(coinBase, period = "max").observeOn(scheduler)

        //zip two chartdata requests
        Single.zip(rsiChartObservable, arimaChartObservable, { t1, t2->
            rsiEvaluator.calculateRsiEntity(t1, riskReward = RsiEntity.RISK_REWARD.HIGH)
                    .observeOn(scheduler)
                    .subscribe ({ rsi ->
                        viewState.setRsi(rsi)
                    }, {
                viewState.showRsiError()
            })
            arimaEvaluator.calculateArima(t2).observeOn(scheduler)
                    .subscribe ({ forecast->
                viewState.setArima(String.format("%.5f", forecast.last()))
            }, {
                viewState.showArimaError()
            })
        }).doAfterSuccess {
            viewState.hideLoading()
        }.subscribe({}, {
            viewState.renderError(it as Exception)
        })
    }

    fun deleteFromCache(coinBase: CoinBase) {
        favCache.deleteFavCoin(coinBase).subscribe()
    }

    fun saveToCache(coinBase: CoinBase) {
        favCache.saveFavCoin(coinBase).subscribe()
    }

    private fun loadImage(url: String) {
        imageLoader.loadImageAsDrawable(url).observeOn(scheduler).subscribe { img ->
            viewState.setLogo(img)
        }
    }


    fun changeArima(period: Int, coinBase: CoinBase?) {
        coinBase?.let { coin-> coinDataRepo
                .getCoinMarketChartData(coin, period = "max").observeOn(scheduler).doOnError {
                    viewState.renderError(it as Exception)
                }.subscribe{chart->
                    arimaEvaluator.calculateArima(chart, predictionPeriod = period).observeOn(scheduler)
                            .subscribe ({ forecast->
                                viewState.setArima(String.format("%.5f", forecast.last()))
                            }, {
                                viewState.showArimaError()
                            })
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        injection.releaseDetailedSubComponent()
    }
}