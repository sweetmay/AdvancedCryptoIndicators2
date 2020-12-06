package com.sweetmay.advancedcryptoindicators2.presenter.list

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CoinsListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView
import java.util.*

open class CoinsListPresenter(private val callback: CoinsListPresenterCallbacks): ICoinsListPresenter{

    val coins = arrayListOf<CoinBase>()

    override fun onFavButtonClicked(view: CoinItemView, state: Boolean) {
        coins[view.getPos()].is_favorite = state
    }

    override fun saveFav(view: CoinItemView) {
        coins[view.getPos()].is_favorite = true
        callback.saveToCache(coins[view.getPos()])
    }

    override fun deleteFav(view: CoinItemView) {
        coins[view.getPos()].is_favorite = false
        callback.deleteFromCache(coins[view.getPos()])
    }

    override fun onItemClick(view: CoinItemView) {
        callback.navigateToDetailedScreen((coins[view.getPos()]))
    }

    override fun bindView(view: CoinItemView) {
        with(view){
            setIcon(coins[view.getPos()].image)
            setName(coins[view.getPos()].name)
            setPrice(coins[view.getPos()].current_price)
            setFavIcon(coins[view.getPos()].is_favorite)
        }
    }

    override fun getCount(): Int {
        return coins.size
    }

}