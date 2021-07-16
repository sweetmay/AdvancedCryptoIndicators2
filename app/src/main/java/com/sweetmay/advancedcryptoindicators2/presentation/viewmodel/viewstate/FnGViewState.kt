package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate

sealed class FnGViewState<out T>{
  data class Success<out T>(val value: T): FnGViewState<T>()
  data class Error(val errorMsg: String): FnGViewState<Nothing>()
  object Loading: FnGViewState<Nothing>()
  object Empty : FnGViewState<Nothing>() {
  }
}