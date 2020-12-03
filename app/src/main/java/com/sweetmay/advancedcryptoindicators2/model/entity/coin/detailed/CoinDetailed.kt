package com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed

data class CoinDetailed(
    val community_data: CommunityData,
    val community_score: Double,
    val id: String,
    val image: Image,
    val links: Links,
    val market_data: MarketData,
    val name: String,
    val sentiment_votes_down_percentage: Double,
    val sentiment_votes_up_percentage: Double,
)