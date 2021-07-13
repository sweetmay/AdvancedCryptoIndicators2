package com.sweetmay.advancedcryptoindicators2.presenter.callback

import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.utils.PagingState

interface CoinsListPresenterCallbacks {
    fun navigateToDetailedScreen(coinView: CoinView)
    fun saveToCache(coinView: CoinView)
    fun deleteFromCache(coinView: CoinView)
    fun loadData()
}