package com.sweetmay.advancedcryptoindicators2.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coin(val name: String,
                val image: String,
                val current_price: Float, ) : Parcelable {
}