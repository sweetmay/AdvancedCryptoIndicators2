package com.sweetmay.advancedcryptoindicators2.presenter.list

import com.sweetmay.advancedcryptoindicators2.presenter.callback.FavListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView

class FavCoinsListPresenter(val callback: FavListPresenterCallbacks): CoinsListPresenter(callback) {

    override fun saveFav(view: CoinItemView) {
        //
    }

    override fun deleteFav(view: CoinItemView) {
        coins[view.getPos()].is_favorite = false
        callback.deleteFromCache(coins[view.getPos()])
        callback.notifyElementChange(view.getPos())
    }

}