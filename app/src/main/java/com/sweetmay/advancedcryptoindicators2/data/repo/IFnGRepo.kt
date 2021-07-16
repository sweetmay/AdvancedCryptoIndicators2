package com.sweetmay.advancedcryptoindicators2.data.repo

import com.sweetmay.advancedcryptoindicators2.domain.model.entity.fng.FnGEntity

interface IFnGRepo {
    suspend fun getFng(limit: String): ResultWrapper<FnGEntity>
}