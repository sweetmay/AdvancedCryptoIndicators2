package com.sweetmay.advancedcryptoindicators2.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class PagingState(
  var firstItem: Int = 1,
  var lastItem: Int = 1,
  var page: Int = 1,
  val pagingConfig: PagingConfig,
  var loading: Boolean = false
) : Parcelable, Observable() {
  companion object{
    const val KEY: String = "PagingState"
  }
}
