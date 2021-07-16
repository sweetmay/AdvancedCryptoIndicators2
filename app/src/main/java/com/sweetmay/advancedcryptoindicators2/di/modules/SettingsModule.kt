//package com.sweetmay.advancedcryptoindicators2.di.modules
//
//import android.content.Context
//import android.content.SharedPreferences
//import com.sweetmay.advancedcryptoindicators2.App
//import com.sweetmay.advancedcryptoindicators2.data.settings.ISettings
//import com.sweetmay.advancedcryptoindicators2.data.settings.Settings
//import dagger.Module
//import dagger.Provides
//import javax.inject.Singleton
//
//@Module
//class SettingsModule {
//
//    @Singleton
//    @Provides
//    fun preferences(app: App): SharedPreferences{
//        return app.getSharedPreferences(app.SETTINGS, Context.MODE_PRIVATE)
//    }
//
//    @Singleton
//    @Provides
//    fun settings(prefs: SharedPreferences, app: App): ISettings{
//        return Settings(prefs, app.applicationContext)
//    }
//}