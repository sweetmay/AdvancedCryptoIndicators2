package com.sweetmay.advancedcryptoindicators2.utils.converter

import android.graphics.Color
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.GeneralInfoCoinDb
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart.ChartData

class Converter {

    fun convertChange(change: Double): ConvertedChange{
        return ConvertedChange(change).convert()
    }

    class ConvertedChange(change: Double){
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

    fun convertChartDataForRsi(chartData: ChartData): ArrayList<Float> {
        val priceList = arrayListOf<Float>()
        for (element in chartData.prices) {
            priceList.add(element[1])
        }
        return priceList
    }

    fun convertChartDataForArima(chartData: ChartData): DoubleArray{
        val priceList = DoubleArray(chartData.prices.size)
        for (i in chartData.prices.indices) {
            priceList[i] = chartData.prices[i][1].toDouble()
        }
        return priceList
    }

    fun convertIdsToString(list: List<CoinDb>): String{
        val result = StringBuilder()
        for (coin in list){
            if(result.isNotEmpty()){
                result.append(",${coin.id}")
            }else {
                result.append(coin.id)
            }
        }
        return result.toString()
    }


    fun convertIdsToStringFromSearch(list: List<GeneralInfoCoinDb>): String{
        val result = StringBuilder()
        for (coin in list){
            if(result.isNotEmpty()){
                result.append(",${coin.id}")
            }else {
                result.append(coin.id)
            }
        }
        return result.toString()
    }

}