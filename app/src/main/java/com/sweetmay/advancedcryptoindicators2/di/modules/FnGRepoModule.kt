package com.sweetmay.advancedcryptoindicators2.di.modules

import com.sweetmay.advancedcryptoindicators2.di.fng.FnGSubComponentScope
import com.sweetmay.advancedcryptoindicators2.model.repo.IFnGRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.FnGRepo
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderFnG
import dagger.Module
import dagger.Provides

@Module
class FnGRepoModule {

    @FnGSubComponentScope
    @Provides
    fun getRepo(apiHolderFnG: ApiHolderFnG): IFnGRepo{
        return FnGRepo(apiHolderFnG)
    }
}