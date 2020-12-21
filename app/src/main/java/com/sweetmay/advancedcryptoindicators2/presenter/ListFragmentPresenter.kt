package com.sweetmay.advancedcryptoindicators2.presenter

import android.widget.ImageView
import com.sweetmay.advancedcryptoindicators2.IAppInjection
import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CoinsListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.presenter.list.CoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoader
import com.sweetmay.advancedcryptoindicators2.view.CoinsListView
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class ListFragmentPresenter(private val injection: IAppInjection) : MvpPresenter<CoinsListView>(), CoinsListPresenterCallbacks {

    init {
        injection.initListComponent()?.inject(this)
    }

    @Inject
    lateinit var coinsRepo: ICoinsListRepo
    @Inject
    lateinit var scheduler: Scheduler
    @Inject
    lateinit var favCache: IFavCoinsCache
    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    companion object{
        var stateRVPos: Int = 0
    }
    private val TAG: String = this::class.java.simpleName


    val coinsListPresenter = CoinsListPresenter(this)


    var pageToLoad = 1

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle()
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
        viewState.showLoading()
            coinsRepo.getCoins(currencyAgainst, page = pageToLoad).observeOn(scheduler).subscribe({list ->
                coinsListPresenter.coins.addAll(list)
                pageToLoad++
                viewState.updateList()
                viewState.hideLoading()
                viewState.restoreRVposition(stateRVPos)
            }, {
                viewState.renderError(it as Exception)
                viewState.hideLoading()
            })
    }

    fun saveRVPos(itemPos: Int) {
        stateRVPos = itemPos
    }

    fun restoreRVState(): Int {
        return stateRVPos
    }

    fun createAdapter(): CoinsListAdapter {
        return CoinsListAdapter(coinsListPresenter, imageLoader)
    }

    override fun onDestroy() {
        super.onDestroy()
        injection.releaseListComponent()
    }


}