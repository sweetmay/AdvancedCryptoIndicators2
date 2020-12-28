package com.sweetmay.advancedcryptoindicators2.model.settings

import android.content.Context
import android.content.SharedPreferences
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CurrentPrice
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.PriceChangePercentage24hInCurrency
import kotlin.properties.Delegates

class Settings(val prefs: SharedPreferences, val context: Context) : ISettings{

    override lateinit var currencyAgainst: String
    override lateinit var order: String

    override var arimaPredictionPeriod by Delegates.notNull<Int>()
    override lateinit var arimaTimeFrame: String

    override lateinit var rsiTimeFrame: String
    override var rsiPeriod by Delegates.notNull<Int>()
    override var rsiRR by Delegates.notNull<Int>()

    init {
        initSettings()
    }

    private fun initSettings() {
        currencyAgainst = prefs.getString(Keys.currencyAgainst.toString(),
                CurrencyAgainst.usd.toString()).toString()
        order = prefs.getString(Keys.order.toString(),
                ListFilter.market_cap_desc.toString()).toString()
        arimaPredictionPeriod = prefs.getInt(Keys.arimaPredictionPeriod.toString(), 30)
        arimaTimeFrame = prefs.getString(Keys.arimaTimeFrame.toString(), "max").toString()
        rsiTimeFrame = prefs.getString(Keys.rsiTimeFrame.toString(), "max").toString()
        rsiPeriod = prefs.getInt(Keys.rsiPeriod.toString(), 14)
        rsiRR = prefs.getInt(Keys.rsiRR.toString(), 1)
        saveSettings()
    }

    enum class Keys{
        order,
        currencyAgainst,
        arimaPredictionPeriod,
        arimaTimeFrame,
        rsiTimeFrame,
        rsiPeriod,
        rsiRR
    }

    enum class ListFilter {
        market_cap_desc,
        gecko_desc,
        gecko_asc,
        market_cap_asc,
        volume_asc,
        volume_desc,
        id_asc,
        id_desc
    }

    enum class CurrencyAgainst {
        btc,
        rub,
        usd
    }


    override fun getPriceByPreference(currentPrice: CurrentPrice)=
            when(currencyAgainst){
            CurrencyAgainst.usd.toString()->currentPrice.usd
            CurrencyAgainst.rub.toString()->currentPrice.rub
            CurrencyAgainst.btc.toString()->currentPrice.btc
                else -> currentPrice.usd
            }

    override fun getChangeByPreference(change: PriceChangePercentage24hInCurrency) =
            when(currencyAgainst){
            CurrencyAgainst.usd.toString()->change.usd
            CurrencyAgainst.rub.toString()->change.rub
            CurrencyAgainst.btc.toString()->change.btc
                else -> change.usd
            }

    override fun getArimaTimeFrameRes(): Int {
        return when(arimaTimeFrame){
            "7"->R.string._7_days
            "14"->R.string._14_days
            "30"->R.string._30_days
            "365"->R.string._365_days
            "max"->R.string._Max
            else -> R.string._Max
        }
    }

    override fun getRsiTimeFrameRes(): Int {
        return when(rsiTimeFrame){
            "7"->R.string._7_days
            "14"->R.string._14_days
            "30"->R.string._30_days
            "365"->R.string._365_days
            "max"->R.string._Max
            else -> R.string._Max
        }
    }

    override fun getRsiRiskRewardRes(): Int {
        return when(rsiRR){
            1-> R.string.lowRR
            2-> R.string.mediumRR
            3-> R.string.highRR
            else-> R.string.lowRR
        }
    }

    override fun setRiskRewardByString(string: String) {
        when(string){
            context.getString(R.string.lowRR)-> rsiRR = 1
            context.getString(R.string.mediumRR)-> rsiRR = 2
            context.getString(R.string.highRR)-> rsiRR = 3
        }
    }


    override fun saveSettings() {
        with(prefs.edit()){
            putString(Keys.currencyAgainst.toString(), currencyAgainst)
            putString(Keys.order.toString(), order)
            putInt(Keys.arimaPredictionPeriod.toString(), arimaPredictionPeriod)
            putString(Keys.arimaTimeFrame.toString(), arimaTimeFrame)
            putString(Keys.rsiTimeFrame.toString(), rsiTimeFrame)
            putInt(Keys.rsiPeriod.toString(), rsiPeriod)
            putInt(Keys.rsiRR.toString(), rsiRR).apply()
        }
    }
}