package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate

sealed class MainListViewState<out T> : FnGViewState<T>() {
  data class Success<out T>(val value: T) : MainListViewState<T>()
  data class Error(val errorMsg: String) : MainListViewState<Nothing>()
  data class ScrollingToLastPos(val pos: Int) : MainListViewState<Nothing>()
  object Loading : MainListViewState<Nothing>()
  object Empty: MainListViewState<Nothing>(){
  }
}