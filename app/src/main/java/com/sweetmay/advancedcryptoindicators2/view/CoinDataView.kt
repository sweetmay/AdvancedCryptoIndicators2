package com.sweetmay.advancedcryptoindicators2.view

import android.graphics.drawable.Drawable
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CoinDetailed
import com.sweetmay.advancedcryptoindicators2.utils.converter.PriceConverter
import com.sweetmay.advancedcryptoindicators2.utils.rsi.RsiEntity
import com.sweetmay.advancedcryptoindicators2.utils.rsi.RsiEvaluator
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface CoinDataView: MvpView {
    fun setPrice(price: String)
    fun set24hChange(convertedChange: PriceConverter.ConvertedChange)
    fun showLoading()
    fun hideLoading()
    fun setTitle(title: String)
    fun setLogo(image: Drawable)
    fun setRsi(rsi: RsiEntity)
}