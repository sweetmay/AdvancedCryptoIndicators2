package com.sweetmay.advancedcryptoindicators2.view.base

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

interface BaseView: MvpView {
    @Skip
    fun renderError(msg: String)
}