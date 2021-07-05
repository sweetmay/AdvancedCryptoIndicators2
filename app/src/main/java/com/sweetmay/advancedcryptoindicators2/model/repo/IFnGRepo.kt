package com.sweetmay.advancedcryptoindicators2.model.repo

import com.sweetmay.advancedcryptoindicators2.model.entity.fng.FnGEntity

interface IFnGRepo {
    suspend fun getFng(limit: String): ResultWrapper<FnGEntity>
}