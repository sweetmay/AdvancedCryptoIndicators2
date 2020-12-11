package com.sweetmay.advancedcryptoindicators2.view

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface CoinsListView: MvpView {
    fun initRv()
    fun updateList()
    fun setTitle()
    fun restoreRVposition(pos: Int)
    fun showLoading()
    fun hideLoading()
    @Skip
    fun selectCoin(coinBase: CoinBase)
}