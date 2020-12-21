package com.sweetmay.advancedcryptoindicators2.view

import android.graphics.drawable.Drawable
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.rsi.RsiEntity
import com.sweetmay.advancedcryptoindicators2.view.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface CoinDataView: BaseView {
    fun setPrice(price: String)
    fun set24hChange(convertedChange: Converter.ConvertedChange)
    fun showLoading()
    fun hideLoading()
    fun setArima(prediction: String)
    fun setTitle(title: String)
    fun setLogo(image: Drawable)
    fun setRsi(rsi: RsiEntity)
    fun showArimaError()
    fun showRsiError()
    fun setFavButton(coinBase: CoinBase)
    fun setSentimentView(value: Int)
}