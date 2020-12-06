package com.sweetmay.advancedcryptoindicators2.presenter

import android.util.Log
import com.sweetmay.advancedcryptoindicators2.model.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CoinsListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.presenter.list.CoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.presenter.list.ICoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.view.CoinsListView
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ListFragmentPresenter(val coinsRepo: ICoinsListRepo, val scheduler: Scheduler, val favCache: IFavCoinsCache): MvpPresenter<CoinsListView>(), CoinsListPresenterCallbacks {

    private val TAG: String = this::class.java.simpleName

    val coinsListPresenter = CoinsListPresenter(this)

    override fun deleteFromCache(coinBase: CoinBase) {
        favCache.deleteFavCoin(coinBase).observeOn(scheduler).subscribe()
    }


    override fun navigateToDetailedScreen(coinBase: CoinBase) {
        viewState.selectCoin(coinBase)
    }

    override fun saveToCache(coinBase: CoinBase) {
        favCache.saveFavCoin(coinBase).observeOn(scheduler).subscribe()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle()
        viewState.initRv()
        loadData()
    }

    private fun loadData(currencyAgainst: String = CoinsListRepo.Currency.usd.toString()) {
        coinsRepo.getCoins(currencyAgainst).observeOn(scheduler).subscribe({list ->
            coinsListPresenter.coins.clear()
            coinsListPresenter.coins.addAll(list)
            viewState.updateList()
        }, {
            Log.d(TAG, it.message.toString())
        })
    }

}