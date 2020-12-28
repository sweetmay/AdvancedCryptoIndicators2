package com.sweetmay.advancedcryptoindicators2.presenter

import android.widget.ImageView
import com.sweetmay.advancedcryptoindicators2.IAppInjection
import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.settings.ISettings
import com.sweetmay.advancedcryptoindicators2.presenter.callback.FavListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.presenter.list.FavCoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoader
import com.sweetmay.advancedcryptoindicators2.view.FavView
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class FavListFragmentPresenter(private val injection: IAppInjection) : MvpPresenter<FavView>(), FavListPresenterCallbacks {

    init {
        injection.initFavComponent()?.inject(this)
    }

    @Inject
    lateinit var coinsRepo: ICoinsListRepo
    @Inject
    lateinit var scheduler: Scheduler
    @Inject
    lateinit var favCache: IFavCoinsCache
    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>
    @Inject
    lateinit var converter: Converter
    @Inject
    lateinit var settings: ISettings

    private val listPresenter = FavCoinsListPresenter(this, converter)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle()
        viewState.initRv()
        loadData()
    }

    override fun navigateToDetailedScreen(coinBase: CoinBase) {
        viewState.navigateToDetailed(coinBase)
    }

    override fun saveToCache(coinBase: CoinBase) {
        favCache.saveFavCoin(coinBase).subscribe()
    }

    override fun deleteFromCache(coinBase: CoinBase) {
        favCache.deleteFavCoin(coinBase).subscribe()
    }

    override fun notifyElementChange(pos: Int) {
        listPresenter.coins.removeAt(pos)
        viewState.notifyItemRemoved(pos, listPresenter.coins.size)
        if (listPresenter.coins.isEmpty()){
            viewState.showNoCoins()

        }
    }

    fun loadData(currencyAgainst: String = settings.currencyAgainst){
        viewState.showLoading()
        favCache.getFavCoins().subscribe { listDb->
            if (listDb.isNotEmpty()){
            coinsRepo.getCoins(currencyAgainst,
                    converter.convertIdsToString(listDb),
                    settings.order)
                    .observeOn(scheduler)
                    .subscribe ({ list->
                        listPresenter.coins.clear()
                        listPresenter.coins.addAll(list)
                        viewState.hideLoading()
                        viewState.updateList()
            }, {
                viewState.renderError(it as Exception)
                })
            }else {
                viewState.hideLoading()
                viewState.showNoCoins()
            }
        }
    }

    fun createAdapter(): CoinsListAdapter {
        return CoinsListAdapter(listPresenter, imageLoader)
    }

    override fun onDestroy() {
        super.onDestroy()
        injection.releaseFavComponent()
    }

}