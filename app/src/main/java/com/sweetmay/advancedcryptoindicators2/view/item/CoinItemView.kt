package com.sweetmay.advancedcryptoindicators2.view.item

interface CoinItemView: IItemView {
    fun setIcon(imgUrl: String)
    fun setName(name: String)
    fun setPrice(price: Float)
}