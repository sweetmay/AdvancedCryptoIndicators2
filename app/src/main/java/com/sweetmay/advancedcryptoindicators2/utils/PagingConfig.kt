package com.sweetmay.advancedcryptoindicators2.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PagingConfig(val pageThreshold: Int, val perPageLimit: Int): Parcelable
