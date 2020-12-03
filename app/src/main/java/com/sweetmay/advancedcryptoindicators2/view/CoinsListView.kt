package com.sweetmay.advancedcryptoindicators2.view

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import moxy.MvpView
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface CoinsListView: MvpView {
    fun initRv()
    fun setTitle()
    fun updateList()
    @Skip
    fun selectCoin(coinBase: CoinBase)
}