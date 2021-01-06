package com.sweetmay.advancedcryptoindicators2.presenter

import android.widget.ImageView
import com.sweetmay.advancedcryptoindicators2.IAppInjection
import com.sweetmay.advancedcryptoindicators2.model.db.cache.IAllCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.settings.ISettings
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CoinsListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.presenter.list.CoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.exception.NoResultsException
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoader
import com.sweetmay.advancedcryptoindicators2.view.SearchView
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class SearchFragmentPresenter(private val injection: IAppInjection) : MvpPresenter<SearchView>(),
    CoinsListPresenterCallbacks {

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
    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>
    @Inject
    lateinit var converter: Converter
    @Inject
    lateinit var settings: ISettings

    private val coinsListPresenter = CoinsListPresenter(this, converter)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRV()
        viewState.setTitle()
        viewState.hideLoading()
    }

    fun searchForCoins(name: String) {
        viewState.showLoading()
        allCoinsCache.findByName(name).observeOn(scheduler).subscribe { list->
            try {
                if(list.isNotEmpty()){
                    coinsRepo.getCoins(settings.currencyAgainst,
                            converter.convertIdsToStringFromSearch(list), settings.order)
                            .observeOn(scheduler)
                            .subscribe ({ coins->
                                coinsListPresenter.coins.clear()
                                coinsListPresenter.coins.addAll(coins)
                                viewState.updateList()
                                viewState.hideLoading()
                            }, {
                                viewState.renderError(it as Exception)
                            })
                }else {
                    throw NoResultsException()
                }
            }catch (e: NoResultsException){
                viewState.hideLoading()
                coinsListPresenter.coins.clear()
                viewState.updateList()
                viewState.renderError(e)
            }
        }
    }

    fun fetchAllcoins() {
        coinsRepo.saveFullList().subscribe ({list->
            allCoinsCache.saveCoins(list).subscribe()
        },{
            viewState.renderError(it as Exception)
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

    fun createAdapter(): CoinsListAdapter {
        return CoinsListAdapter(coinsListPresenter, imageLoader)
    }

    override fun onDestroy() {
        super.onDestroy()
        injection.releaseSearchComponent()
    }
}