//package com.sweetmay.advancedcryptoindicators2.di.modules
//
//import com.sweetmay.advancedcryptoindicators2.App
//import dagger.Module
//import dagger.Provides
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
//import io.reactivex.rxjava3.core.Scheduler
//
//@Module
//class AppModule(val app: App) {
//
//    @Provides
//    fun appInstance(): App{
//        return app
//    }
//
//    @Provides
//    fun scheduler(): Scheduler {
//        return AndroidSchedulers.mainThread()
//    }
//}