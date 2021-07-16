package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel

import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.domain.usecase.GetFngDataUseCase
import com.sweetmay.advancedcryptoindicators2.domain.usecase.IGetFngDataUseCase
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.fng.FnGView
import com.sweetmay.advancedcryptoindicators2.data.repo.IFnGRepo
import com.sweetmay.advancedcryptoindicators2.data.repo.retrofit.FnGRepo
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.base.BaseViewModel
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.FnGViewState
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderFnG
import kotlinx.coroutines.launch

class FearGreedViewModel: BaseViewModel<FnGView>() {

  private val fngRepo: IFnGRepo = FnGRepo(ApiHolderFnG(App.instance.BASE_URL_FNG))
  private val getFngUseCase: IGetFngDataUseCase = GetFngDataUseCase(fngRepo, "31")

  init {
    _uiState.value = FnGViewState.Loading
    launch {
      loadData()
    }
  }

  private suspend fun loadData(){
    _uiState.value = getFngUseCase.getFngView()
  }
}