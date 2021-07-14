package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate

sealed class FavViewState<out T>{
  data class Success<out T>(val value: T): FavViewState<T>()
  data class Error(val errorMsg: String): FavViewState<Nothing>()
  object Loading: FavViewState<Nothing>()
  object Empty: FavViewState<Nothing>()
}
