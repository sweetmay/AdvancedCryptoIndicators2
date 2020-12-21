package com.sweetmay.advancedcryptoindicators2.model.settings

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CurrentPrice

class Settings: ISettings {

    val preference = CurrencyAgainst.usd

    enum class CurrencyAgainst {
        btc,
        rub,
        usd
    }

    override fun getPriceByPreference(currentPrice: CurrentPrice): Float {
        return when(preference){
            CurrencyAgainst.usd->currentPrice.usd
            CurrencyAgainst.rub->currentPrice.rub
            CurrencyAgainst.btc->currentPrice.btc
        }
    }
}