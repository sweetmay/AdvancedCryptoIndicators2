package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate

sealed class MainListViewState<out T>{
  data class Success<out T>(val value: T): MainListViewState<T>()
  data class Error(val errorMsg: String): MainListViewState<Nothing>()
  object Loading: MainListViewState<Nothing>()
  object Empty: MainListViewState<Nothing>()
}