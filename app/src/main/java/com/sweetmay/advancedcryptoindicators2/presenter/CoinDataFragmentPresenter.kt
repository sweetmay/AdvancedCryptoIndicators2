package com.sweetmay.advancedcryptoindicators2.presenter

import android.util.Log
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CoinDetailed
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinDataRepo
import com.sweetmay.advancedcryptoindicators2.utils.rsi.IRsiEvaluator
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.utils.converter.PriceConverter
import com.sweetmay.advancedcryptoindicators2.view.CoinDataView
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoaderAsDrawable
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class CoinDataFragmentPresenter(val coinDataRepo: ICoinDataRepo, val scheduler: Scheduler, val imageLoader: IImageLoaderAsDrawable, val rsiEvaluator: IRsiEvaluator): MvpPresenter<CoinDataView>() {


    fun loadCoinData(coinBase: CoinBase) {

        viewState.showLoading()
        viewState.setTitle(coinBase.name)

        coinDataRepo.getCoin(coinBase).observeOn(scheduler).subscribe { coinDetailed ->
            viewState.setPrice("$ " + coinDetailed.market_data.current_price.usd.toString())
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
        coinDataRepo.getCoinMarketChartData(coinBase, CoinsListRepo.Currency.usd.toString()).observeOn(scheduler).subscribe { chartData->
            rsiEvaluator.calculateRsi(chartData, 14).observeOn(scheduler).subscribe { rsi->
                viewState.setRsi(rsi)
            }
        }
    }

    private fun loadImage(url: String) {
        imageLoader.loadImageAsDrawable(url).observeOn(scheduler).subscribe{img->
            viewState.setLogo(img)
        }
    }

}