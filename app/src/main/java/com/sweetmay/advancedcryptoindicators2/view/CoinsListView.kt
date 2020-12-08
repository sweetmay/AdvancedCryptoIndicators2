package com.sweetmay.advancedcryptoindicators2.view

import android.os.Parcelable
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
    fun updateList()
    fun setTitle()
    fun restoreRVposition(pos: Int)
    @Skip
    fun selectCoin(coinBase: CoinBase)
}