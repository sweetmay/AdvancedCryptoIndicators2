package com.sweetmay.advancedcryptoindicators2.domain

import com.sweetmay.advancedcryptoindicators2.model.entity.fng.FnGView
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.base.MainListViewState

interface IGetFngDataUseCase {
  suspend fun getFngView(): MainListViewState<FnGView>
}