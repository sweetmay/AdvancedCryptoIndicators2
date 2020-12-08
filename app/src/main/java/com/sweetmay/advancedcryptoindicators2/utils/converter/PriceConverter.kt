package com.sweetmay.advancedcryptoindicators2.utils.converter

import android.graphics.Color

class PriceConverter {
    companion object{
        fun convertChange(change: Double): ConvertedChange{
            return ConvertedChange(change).convert()
        }
    }
    class ConvertedChange(val change: Double){
        val color: Int
        val convertedPriceString: String

        init {
            if(change>0){
                color = Color.GREEN
                convertedPriceString = "+" + String.format("%.2f", change) + "%"
            }else {
                color = Color.RED
                convertedPriceString = String.format("%.2f", change) + "%"
            }
        }
        fun convert(): ConvertedChange {
            return this
        }
    }
}