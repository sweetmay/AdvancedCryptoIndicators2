package com.sweetmay.advancedcryptoindicators2.presenter.list

import com.sweetmay.advancedcryptoindicators2.presenter.callback.FavListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView

class FavCoinsListPresenter(private val callback: FavListPresenterCallbacks,
                            val converter: Converter)
    : CoinsListPresenter(callback, converter) {

    override fun saveFav(view: CoinItemView) {
        //
    }

    override fun deleteFav(view: CoinItemView) {
        coins[view.getPos()].is_favorite = false
        callback.deleteFromCache(coins[view.getPos()])
        callback.notifyElementChange(view.getPos())
    }

}