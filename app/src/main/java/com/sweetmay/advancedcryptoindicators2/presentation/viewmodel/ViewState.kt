package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel

sealed class ViewState<out T>{
  data class Success<out T>(val value: T): ViewState<T>()
  data class Error(val errorMsg: String): ViewState<Nothing>()
  object Loading: ViewState<Nothing>()
  object Empty: ViewState<Nothing>()
}