package com.sweetmay.advancedcryptoindicators2.view

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.view.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface SearchView: BaseView {
    fun initRV()
    fun setTitle()
    fun updateList()
    fun showLoading()
    fun hideLoading()
    @Skip
    fun selectCoin(coinBase: CoinBase)
}