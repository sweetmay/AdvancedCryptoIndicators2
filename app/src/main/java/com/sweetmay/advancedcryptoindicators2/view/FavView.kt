package com.sweetmay.advancedcryptoindicators2.view

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface FavView : MvpView {
    fun initRv()
    fun updateList()
    fun notifyItemRemoved(pos: Int, newSize: Int)
    @Skip
    fun navigateToDetailed(coinBase: CoinBase)
    fun showNoCoins()
}