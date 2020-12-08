package com.sweetmay.advancedcryptoindicators2.model.entity.coin.chart

data class ChartData(
    val market_caps: List<List<Float>>,
    val prices: List<List<Float>>,
)