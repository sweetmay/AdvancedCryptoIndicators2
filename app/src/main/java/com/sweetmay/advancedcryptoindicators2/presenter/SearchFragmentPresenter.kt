package com.sweetmay.advancedcryptoindicators2.presenter

import android.util.Log
import com.sweetmay.advancedcryptoindicators2.IAppInjection
import com.sweetmay.advancedcryptoindicators2.model.db.cache.IAllCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CoinsListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.presenter.list.CoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.view.SearchView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class SearchFragmentPresenter(private val injection: IAppInjection): MvpPresenter<SearchView>(), CoinsListPresenterCallbacks {

    init {
        injection.initSearchComponent()?.inject(this)
    }

    @Inject
    lateinit var scheduler: Scheduler
    @Inject
    lateinit var allCoinsCache: IAllCoinsCache
    @Inject
    lateinit var coinsRepo: ICoinsListRepo
    @Inject
    lateinit var favCache: IFavCoinsCache

    val coinsListPresenter = CoinsListPresenter(this)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRV()
        viewState.setTitle()
        viewState.hideLoading()
    }

    fun searchForCoins(name: String) {
        viewState.showLoading()
        allCoinsCache.findByName(name).subscribe { list->
            if(list.isNotEmpty()){
                coinsRepo.getCoins(CoinsListRepo.Currency.usd.toString(),
                        ids = Converter().convertIdsToStringFromSearch(list))
                        .observeOn(scheduler)
                        .subscribe ({ coins->
                            coinsListPresenter.coins.clear()
                            coinsListPresenter.coins.addAll(coins)
                            viewState.updateList()
                            viewState.hideLoading()
                        }, {
                            Log.d("A", it.toString())
                        })
            }
        }
    }

    fun fetchAllcoins() {
        coinsRepo.saveFullList().subscribe ({list->
            allCoinsCache.saveCoins(list).subscribe()
        },{
            viewState.renderError(it.message ?: "Error")
        })
    }

    override fun navigateToDetailedScreen(coinBase: CoinBase) {
        viewState.selectCoin(coinBase)
    }

    override fun saveToCache(coinBase: CoinBase) {
        favCache.saveFavCoin(coinBase).subscribe()
    }

    override fun deleteFromCache(coinBase: CoinBase) {
        favCache.deleteFavCoin(coinBase).subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        injection.releaseSearchComponent()
    }
}