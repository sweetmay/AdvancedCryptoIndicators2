package com.sweetmay.advancedcryptoindicators2.domain

import com.sweetmay.advancedcryptoindicators2.model.entity.fng.FnGData
import com.sweetmay.advancedcryptoindicators2.model.entity.fng.FnGView
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.ViewState

interface IGetFngDataUseCase {
  suspend fun getFngView(): ViewState<FnGView>
}