package com.sweetmay.advancedcryptoindicators2.model.entity.coin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinBase(val id: String,
                    val name: String,
                    val image: String,
                    val current_price: Float,
                    val price_change_percentage_24h: Float,
                    var is_favorite: Boolean = false) : Parcelable