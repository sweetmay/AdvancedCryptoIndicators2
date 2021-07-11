package com.sweetmay.advancedcryptoindicators2.presenter.callback

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase

interface CoinsListPresenterCallbacks {
    fun navigateToDetailedScreen(coinBase: CoinBase)
    fun saveToCache(coinBase: CoinBase)
    fun deleteFromCache(coinBase: CoinBase)
    fun loadMore(pageToLoad: Int)
}