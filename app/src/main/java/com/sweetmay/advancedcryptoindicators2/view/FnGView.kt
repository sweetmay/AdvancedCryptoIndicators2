package com.sweetmay.advancedcryptoindicators2.view

import com.sweetmay.advancedcryptoindicators2.view.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface FnGView: BaseView {
    fun setTitle()
    fun showLoading()
    fun hideLoading()
    fun showData(fng: Int)
}