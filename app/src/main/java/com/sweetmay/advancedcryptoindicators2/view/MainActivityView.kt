package com.sweetmay.advancedcryptoindicators2.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainActivityView: MvpView {
    fun initBottomNav()
}