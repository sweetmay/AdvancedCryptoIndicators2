package com.sweetmay.advancedcryptoindicators2.domain.usecase

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.fng.FnGView
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.FnGViewState

interface IGetFngDataUseCase {
  suspend fun getFngView(): FnGViewState<FnGView>
}