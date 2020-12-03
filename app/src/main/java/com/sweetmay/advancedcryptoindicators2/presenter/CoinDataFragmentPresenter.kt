package com.sweetmay.advancedcryptoindicators2.presenter

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinDataRepo
import com.sweetmay.advancedcryptoindicators2.view.CoinDataView
import com.sweetmay.advancedcryptoindicators2.view.image.IImageLoaderAsDrawable
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class CoinDataFragmentPresenter(val coinDataRepo: ICoinDataRepo, val scheduler: Scheduler, val imageLoader: IImageLoaderAsDrawable): MvpPresenter<CoinDataView>() {

    fun loadCoinData(coinBase: CoinBase) {
        coinDataRepo.getCoin(coinBase).observeOn(scheduler).subscribe { coinDetailed->
            viewState.setPrice("$ " + coinDetailed.market_data.current_price.usd.toString())
            viewState.set24hChange(coinDetailed.market_data.price_change_percentage_24h_in_currency.usd.toString())
            loadImage(coinDetailed.image.small)
            viewState.setTitle(coinBase.name)
        }
    }

    private fun loadImage(url: String) {
        imageLoader.loadImageAsDrawable(url).observeOn(scheduler).subscribe{img->
            viewState.setLogo(img)
        }
    }

}