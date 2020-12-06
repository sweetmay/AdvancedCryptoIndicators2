package com.sweetmay.advancedcryptoindicators2.model.entity.coin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinBase(val id: String,
                    val name: String,
                    val image: String,
                    val current_price: Float,
                    var is_favorite: Boolean = false) : Parcelable {
}