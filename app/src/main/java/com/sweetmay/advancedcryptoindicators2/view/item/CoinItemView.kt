package com.sweetmay.advancedcryptoindicators2.view.item

import com.sweetmay.advancedcryptoindicators2.utils.converter.PriceConverter
import com.sweetmay.advancedcryptoindicators2.view.custom.FavButton

interface CoinItemView: IItemView {
    fun setIcon(imgUrl: String)
    fun setName(name: String)
    fun setPrice(price: Float)
    fun setFavIcon(boolean: Boolean)
    fun setPriceChange(change: PriceConverter.ConvertedChange)
}