package com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinView(val id: String,
                    val name: String,
                    val image: String,
                    val current_price: Double,
                    val price_change_percentage_24h: Double,
                    var is_favorite: Boolean = false) : Parcelable