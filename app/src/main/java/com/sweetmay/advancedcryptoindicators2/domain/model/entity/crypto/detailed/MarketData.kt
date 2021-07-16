package com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.detailed

data class MarketData(
    val circulating_supply: Double,
    val current_price: CurrentPrice,
    val market_cap: MarketCap,
    val max_supply: Double,
    val price_change_percentage_24h_in_currency: PriceChangePercentage24hInCurrency,
    val price_change_percentage_14d_in_currency: PriceChangePercentage14dInCurrency,
    val price_change_percentage_1h_in_currency: PriceChangePercentage1hInCurrency,
    val price_change_percentage_7h_in_currency: PriceChangePercentage7dInCurrency,
    val total_supply: Double,
)