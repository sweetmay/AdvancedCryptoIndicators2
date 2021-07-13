package com.sweetmay.advancedcryptoindicators2.domain

import com.sweetmay.advancedcryptoindicators2.model.entity.fng.FnGEntity
import com.sweetmay.advancedcryptoindicators2.model.entity.fng.FnGView
import com.sweetmay.advancedcryptoindicators2.model.repo.IFnGRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.ViewState

class GetFngDataUseCase(val fngRepo: IFnGRepo, val limit: String) : IGetFngDataUseCase {
  override suspend fun getFngView(): ViewState<FnGView> {
    return when (val result = fngRepo.getFng(limit)) {
      is ResultWrapper.Success -> {
        convertToViewState(result.value)
      }
      is ResultWrapper.Error -> {
        ViewState.Error(result.errorMsg)
      }
    }
  }

  private fun convertToViewState(fnGEntity: FnGEntity): ViewState<FnGView> {
    val data = fnGEntity.data
    return ViewState.Success(FnGView(
      Pair(data[0].value.toInt(), data[0].value_classification),
      Pair(data[1].value.toInt(), data[1].value_classification),
      Pair(data[6].value.toInt(), data[6].value_classification),
      Pair(data[30].value.toInt(), data[30].value_classification),
    ))
  }
}