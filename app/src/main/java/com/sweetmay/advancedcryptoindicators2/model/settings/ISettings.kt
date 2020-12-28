package com.sweetmay.advancedcryptoindicators2.model.settings

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CurrentPrice
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.PriceChangePercentage24hInCurrency

interface ISettings {

    var arimaPredictionPeriod: Int
    var rsiPeriod: Int
    var currencyAgainst: String
    var order: String
    var rsiTimeFrame: String
    var arimaTimeFrame: String

    fun getPriceByPreference(currentPrice: CurrentPrice): Float
    fun getChangeByPreference(change: PriceChangePercentage24hInCurrency): Float
    fun getArimaTimeFrameRes(): Int
    fun getRsiTimeFrameRes(): Int
    fun saveSettings()

}