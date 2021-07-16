package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.base

import androidx.lifecycle.ViewModel
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.FnGViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

open class BaseViewModel<T>: ViewModel(), CoroutineScope{

  override val coroutineContext: CoroutineContext by lazy {
    Dispatchers.Default + Job()
  }

  protected val _uiState = MutableStateFlow<FnGViewState<T>>(FnGViewState.Empty)
  val uiState: StateFlow<FnGViewState<T>> = _uiState

  override fun onCleared() {
    coroutineContext.cancel()
    super.onCleared()
  }
}