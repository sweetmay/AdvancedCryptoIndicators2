package com.sweetmay.advancedcryptoindicators2.utils.rsi

class RsiEntity(prices: List<Float> = emptyList(),
                period: Int,
                rr: Int) {

    private var basePerc: Float = 0.1f * rr
    private val currentPrice = prices[prices.size-1]

    val rsi = calculateRSI(prices, period)

    var indicatorColor: Int = 0
    val signalStrength: Float
    val possibleTarget: Float
    val stopLoss: Float
    val isPositive: Boolean = rsi <= 50
    var possibleTargetPerc = 0f
    var possibleSLPerc = 0f

    init {
        signalStrength = calculateStrength()
        basePerc = calculateBasePercent()
        possibleTarget = calculateTarget(currentPrice)
        stopLoss = calculateSL(currentPrice)
    }

    private fun calculateStrength(): Float {
        return if (isPositive){
            (100-rsi-50)/50*100
        }else {
            (rsi-50)/50*100
        }
    }

    private fun calculateBasePercent(): Float {
        return basePerc*(signalStrength/100)
    }

    private fun calculateTarget(currentPrice: Float): Float {
        return if (isPositive) {
            val temp = currentPrice * (1+basePerc)
            possibleTargetPerc = (1-currentPrice/temp) * 100
            temp
        }else {
            val temp = currentPrice * (1-basePerc)
            possibleTargetPerc = (currentPrice/temp-1) * 100
            temp
        }
    }

    private fun calculateSL(currentPrice: Float): Float {
        return if (isPositive) {
            val temp = currentPrice * (1 - basePerc / 3)
            possibleSLPerc = (currentPrice/temp-1) * 100
            temp
        } else {
            val temp = currentPrice * (1 + basePerc / 3)
            possibleSLPerc = (1-currentPrice/temp) * 100
            temp
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