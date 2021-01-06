package com.sweetmay.advancedcryptoindicators2.view

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.view.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface CoinsListView: BaseView {
    fun initRv()
    fun updateList()
    fun setTitle()
    fun restoreRVPosition(pos: Int)
    fun showLoading()
    fun hideLoading()
    @Skip
    fun selectCoin(coinBase: CoinBase)
}