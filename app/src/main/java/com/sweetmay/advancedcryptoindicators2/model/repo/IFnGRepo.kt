package com.sweetmay.advancedcryptoindicators2.model.repo

import com.sweetmay.advancedcryptoindicators2.model.entity.fng.FnGEntity
import io.reactivex.rxjava3.core.Single

interface IFnGRepo {
    fun getFnG(limit: String): Single<FnGEntity>
}