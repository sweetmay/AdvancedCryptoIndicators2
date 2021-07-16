package com.sweetmay.advancedcryptoindicators2.data.repo

sealed class ResultWrapper<out T> {
  data class Success<out T>(val value: T): ResultWrapper<T>()
  data class Error(val errorMsg: String): ResultWrapper<Nothing>()
}