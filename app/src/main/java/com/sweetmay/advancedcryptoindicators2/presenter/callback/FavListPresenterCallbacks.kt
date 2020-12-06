package com.sweetmay.advancedcryptoindicators2.presenter.callback

interface FavListPresenterCallbacks: CoinsListPresenterCallbacks {
    fun notifyElementChange(pos: Int)
}