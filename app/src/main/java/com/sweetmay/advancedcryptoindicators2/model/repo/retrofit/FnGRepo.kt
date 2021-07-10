package com.sweetmay.advancedcryptoindicators2.model.repo.retrofit

import com.sweetmay.advancedcryptoindicators2.model.entity.fng.FnGEntity
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper
import com.sweetmay.advancedcryptoindicators2.model.repo.IFnGRepo
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderFnG

class FnGRepo(private val apiHolderFnG: ApiHolderFnG) : IFnGRepo {
  override suspend fun getFng(limit: String): ResultWrapper<FnGEntity> {
    return try {
      ResultWrapper.Success(apiHolderFnG.dataSourceAlternative.getFngData(limit))
    }catch (e: Throwable){
      ResultWrapper.Error(e.message?: "Api Exception")
    }
  }
}