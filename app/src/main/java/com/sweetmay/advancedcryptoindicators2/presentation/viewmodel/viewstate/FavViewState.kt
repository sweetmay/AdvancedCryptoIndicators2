package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate

sealed class FavViewState<out T>: FnGViewState<T>(){
  data class Success<out T>(val value: T): FavViewState<T>()
  data class Error(val errorMsg: String): FavViewState<Nothing>()
  data class RemovingElement(val pos: Int) : FnGViewState<Nothing>()
  object Loading: FavViewState<Nothing>()
  object Empty: FavViewState<Nothing>()
}
