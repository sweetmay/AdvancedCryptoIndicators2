package com.sweetmay.advancedcryptoindicators2.di.app

import com.sweetmay.advancedcryptoindicators2.di.fav.FavSubComponent
import com.sweetmay.advancedcryptoindicators2.di.fng.FnGSubComponent
import com.sweetmay.advancedcryptoindicators2.di.list.ListSubComponent
import com.sweetmay.advancedcryptoindicators2.di.modules.ApiCGModule
import com.sweetmay.advancedcryptoindicators2.di.modules.AppModule
import com.sweetmay.advancedcryptoindicators2.di.modules.ConverterModule
import com.sweetmay.advancedcryptoindicators2.di.modules.SettingsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ApiCGModule::class,
    ConverterModule::class,
    SettingsModule::class
])
interface AppComponent {
    fun listSubComponent(): ListSubComponent
    fun fngComponent(): FnGSubComponent
    fun favComponent(): FavSubComponent
}