package com.sweetmay.advancedcryptoindicators2.presenter

import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.model.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CoinsListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.presenter.list.CoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.view.CoinsListView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class ListFragmentPresenter : MvpPresenter<CoinsListView>(), CoinsListPresenterCallbacks {

    init {
        App.instance.initListComponent()?.inject(this)
    }

    @Inject
    lateinit var coinsRepo: ICoinsListRepo
    @Inject
    lateinit var scheduler: Scheduler
    @Inject
    lateinit var favCache: IFavCoinsCache

    companion object{
        var stateRVPos = 0
    }

    private val TAG: String = this::class.java.simpleName

    val coinsListPresenter = CoinsListPresenter(this)


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle()
        viewState.showLoading()
        viewState.initRv()
        loadData()
    }

    override fun deleteFromCache(coinBase: CoinBase) {
        favCache.deleteFavCoin(coinBase).subscribe()
    }

    override fun navigateToDetailedScreen(coinBase: CoinBase) {
        viewState.selectCoin(coinBase)
    }


    override fun saveToCache(coinBase: CoinBase) {
        favCache.saveFavCoin(coinBase).subscribe()
    }

    fun loadData(currencyAgainst: String = CoinsListRepo.Currency.usd.toString()) {
        coinsRepo.getCoins(currencyAgainst).observeOn(scheduler).subscribe({list ->
            coinsListPresenter.coins.clear()
            coinsListPresenter.coins.addAll(list)
            viewState.updateList()
            viewState.restoreRVposition(stateRVPos)
            viewState.hideLoading()
        }, {
            viewState.renderError(it.message?: "Error")
        })
    }

    fun saveRVPos(itemPos: Int) {
        stateRVPos = itemPos
    }

    fun restoreRVState(): Int {
        return stateRVPos
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.releaseListComponent()
    }
}