package com.sweetmay.advancedcryptoindicators2.presenter

import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.model.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presenter.callback.FavListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.presenter.list.FavCoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.view.FavView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class FavListPresenter : MvpPresenter<FavView>(), FavListPresenterCallbacks {

    val listPresenter = FavCoinsListPresenter(this)

    init {
        App.instance.initFavComponent()?.inject(this)
    }

    @Inject
    lateinit var coinsRepo: ICoinsListRepo
    @Inject
    lateinit var scheduler: Scheduler
    @Inject
    lateinit var favCache: IFavCoinsCache

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle()
        viewState.showLoading()
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

    fun loadData(currencyAgainst: String = CoinsListRepo.Currency.usd.toString()){
        favCache.getFavCoins().subscribe { listDb->
            if (listDb.isNotEmpty()){
            coinsRepo.getCoins(currencyAgainst, ids = convertIdsToString(listDb)).observeOn(scheduler)
                    .subscribe ({ list->
                        listPresenter.coins.clear()
                        listPresenter.coins.addAll(list)
                        viewState.hideLoading()
                        viewState.updateList()
            }, {
                viewState.renderError(it.message?:"Error")
                })
            }else {
                viewState.showNoCoins()
            }
        }
    }

    private fun convertIdsToString(list: List<CoinDb>): String{
        val result = StringBuilder()
        for (coin in list){
            if(result.isNotEmpty()){
                result.append(",${coin.id}")
            }else {
                result.append(coin.id)
            }
        }
        return result.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.releaseFavComponent()
    }

}