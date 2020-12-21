package com.sweetmay.advancedcryptoindicators2.model.settings

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CurrentPrice

interface ISettings {
    fun getPriceByPreference(currentPrice: CurrentPrice): Float
}