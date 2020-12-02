package com.sweetmay.advancedcryptoindicators2.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface CoinsListView: MvpView {
    fun initRv()
    fun updateList()
}