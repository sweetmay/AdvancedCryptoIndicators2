package com.sweetmay.advancedcryptoindicators2.view

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.view.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface FavView : BaseView {
    fun initRv()
    fun updateList()
    fun notifyItemRemoved(pos: Int, newSize: Int)
    fun setTitle()
    fun showLoading()
    fun hideLoading()
    @Skip
    fun navigateToDetailed(coinBase: CoinBase)
    fun showNoCoins()
}