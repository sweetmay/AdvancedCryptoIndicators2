package com.sweetmay.advancedcryptoindicators2.model.repo

import com.sweetmay.advancedcryptoindicators2.model.entity.fng.FnGEntity
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderFnG
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class FnGRepo(val apiHolderFnG: ApiHolderFnG): IFnGRepo {
    override fun getFnG(limit: String): Single<FnGEntity> {
        return apiHolderFnG.dataSourceAlternative.getFngData(limit).subscribeOn(Schedulers.io())
    }
}