package com.sweetmay.advancedcryptoindicators2.domain.usecase

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.fng.FnGEntity
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.fng.FnGView
import com.sweetmay.advancedcryptoindicators2.data.repo.IFnGRepo
import com.sweetmay.advancedcryptoindicators2.data.repo.ResultWrapper
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.FnGViewState

class GetFngDataUseCase(val fngRepo: IFnGRepo, val limit: String) : IGetFngDataUseCase {
  override suspend fun getFngView(): FnGViewState<FnGView> {
    return when (val result = fngRepo.getFng(limit)) {
      is ResultWrapper.Success -> {
        convertToViewState(result.value)
      }
      is ResultWrapper.Error -> {
        FnGViewState.Error(result.errorMsg)
      }
    }
  }

  private fun convertToViewState(fnGEntity: FnGEntity): FnGViewState<FnGView> {
    val data = fnGEntity.data
    return FnGViewState.Success(FnGView(
      Pair(data[0].value.toInt(), data[0].value_classification),
      Pair(data[1].value.toInt(), data[1].value_classification),
      Pair(data[6].value.toInt(), data[6].value_classification),
      Pair(data[30].value.toInt(), data[30].value_classification),
    ))
  }
}