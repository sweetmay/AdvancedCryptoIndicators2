package com.sweetmay.advancedcryptoindicators2.domain.model.entity.fng

data class FnGView(
  val currentFng: Pair<Int, String>,
  val yesterdayFng: Pair<Int, String>,
  val lastWeekFng: Pair<Int, String>,
  val lastMonthFng: Pair<Int, String>
)
