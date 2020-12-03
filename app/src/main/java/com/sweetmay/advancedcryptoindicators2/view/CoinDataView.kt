package com.sweetmay.advancedcryptoindicators2.view

import android.graphics.drawable.Drawable
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CoinDetailed
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface CoinDataView: MvpView {
    fun setPrice(price: String)
    fun set24hChange(change: String)
    fun setTitle(string: String)
    fun setLogo(image: Drawable)
}