//package com.sweetmay.advancedcryptoindicators2.di.modules
//
//import com.sweetmay.advancedcryptoindicators2.App
//import com.sweetmay.advancedcryptoindicators2.di.fng.FnGSubComponentScope
//import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderFnG
//import dagger.Module
//import dagger.Provides
//import javax.inject.Named
//
//@Module
//class ApiFnGModule {
//
//    @FnGSubComponentScope
//    @Provides
//    @Named("base_url_fng")
//    fun baseUrl(app: App): String{
//        return app.BASE_URL_FNG
//    }
//
//    @FnGSubComponentScope
//    @Provides
//    fun getApi(@Named ("base_url_fng") url: String): ApiHolderFnG{
//        return ApiHolderFnG(url)
//    }
//}