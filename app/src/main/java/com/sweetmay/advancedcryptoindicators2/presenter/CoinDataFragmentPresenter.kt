package com.sweetmay.advancedcryptoindicators2.presenter

import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CoinDetailed
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinDataRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.utils.converter.PriceConverter
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoaderAsDrawable
import com.sweetmay.advancedcryptoindicators2.utils.rsi.IRsiEvaluator
import com.sweetmay.advancedcryptoindicators2.utils.rsi.RsiEntity
import com.sweetmay.advancedcryptoindicators2.view.CoinDataView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class CoinDataFragmentPresenter : MvpPresenter<CoinDataView>() {

    init {
        App.instance.coinDetailedSubComponent?.inject(this)
    }

    @Inject
    lateinit var coinDataRepo: ICoinDataRepo
    @Inject
    lateinit var scheduler: Scheduler
    @Inject
    lateinit var imageLoader: IImageLoaderAsDrawable
    @Inject
    lateinit var rsiEvaluator: IRsiEvaluator


    fun loadCoinData(coinBase: CoinBase) {

        viewState.showLoading()
        viewState.setTitle(coinBase.name)

        coinDataRepo.getCoin(coinBase).observeOn(scheduler).subscribe { coinDetailed ->
            viewState.setPrice( coinDetailed.market_data.current_price.usd.toString() + "$ " )
            setChange(coinDetailed)
            loadImage(coinDetailed.image.small)
            viewState.hideLoading()
        }
        loadChartData(coinBase)
    }

    private fun setChange(coinDetailed: CoinDetailed) {
        val change = coinDetailed.market_data.price_change_percentage_24h_in_currency.usd
        val convertedChange = PriceConverter.convertChange(change)
        viewState.set24hChange(convertedChange)
    }

    private fun loadChartData(coinBase: CoinBase) {
        coinDataRepo.getCoinMarketChartData(coinBase, CoinsListRepo.Currency.usd.toString()).observeOn(scheduler).subscribe { chartData ->
            rsiEvaluator.calculateRsiEntity(chartData, riskReward = RsiEntity.RISK_REWARD.HIGH).observeOn(scheduler).subscribe { rsi ->
                viewState.setRsi(rsi)
            }
        }
    }

    private fun loadImage(url: String) {
        imageLoader.loadImageAsDrawable(url).observeOn(scheduler).subscribe { img ->
            viewState.setLogo(img)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.releaseDetailedSubComponent()
    }
}