package com.sweetmay.advancedcryptoindicators2.utils.rsi

class RsiEntity(prices: List<Float> = emptyList(),
                period: Int,
                val rr: Float = RISK_REWARD.LOW) {

    object RISK_REWARD{
        val LOW: Float = 0.33f
        val MEDIUM: Float = 0.66f
        val HIGH: Float = 0.99f
    }

    private var basePerc: Float = 0.03f
    private val currentPrice = prices[prices.size-1]

    val rsi = calculateRSI(prices, period)

    var indicatorColor: Int = 0
    val signalStrength: Float
    val possibleTarget: Float
    val stopLoss: Float
    val isPositive: Boolean = rsi <= 50

    init {
        signalStrength = calculateStrenth()
        basePerc = calculateBasePercent()
        possibleTarget = calculateTarget(currentPrice)
        stopLoss = calculateSL(currentPrice)
    }

    private fun calculateStrenth(): Float {
        return if (isPositive){
            100-rsi
        }else {
            rsi
        }
    }

    private fun calculateBasePercent(): Float {
        return basePerc*(1+signalStrength/100)*rr
    }

    private fun calculateTarget(currentPrice: Float): Float {
        return if (isPositive) {
            val temp = currentPrice * (1+basePerc)
            temp
        }else {
            val temp = currentPrice * (1-basePerc)
            temp
        }
    }

    private fun calculateSL(currentPrice: Float): Float {
        return if (isPositive) {
            currentPrice * (1 - basePerc / 3)
        } else {
            currentPrice * (1 + basePerc / 3)
        }
    }

    private fun calculateRSI(prices: List<Float>, period: Int): Float {
        var avgGain = 0f
        var avgLoss = 0f
        var upNum = 0
        var downNum = 0

        for (i in 0 until prices.size - period) {
            if (i == 0) {
                continue
            }
            if (prices[i] > prices[i - 1]) {
                avgGain += prices[i] - prices[i - 1]
                upNum++
            }
            if (prices[i] < prices[i - 1]) {
                avgLoss += prices[i - 1] - prices[i]
                downNum++
            }
        }
        if (upNum != 0) {
            avgGain /= upNum.toFloat()
        }
        if (downNum != 0) {
            avgLoss /= downNum.toFloat()
        }

        for (i in prices.size - period until prices.size) {
            if (prices[i] > prices[i - 1]) {
                avgGain = (avgGain * (period - 1) + prices[i] - prices[i - 1]) / period
            }
            if (prices[i] < prices[i - 1]) {
                avgLoss = (avgLoss * (period - 1) + prices[i - 1] - prices[i]) / period
            }
        }
        val rs = avgGain / avgLoss
        return 100 - (100 / (1 + rs))
    }

}