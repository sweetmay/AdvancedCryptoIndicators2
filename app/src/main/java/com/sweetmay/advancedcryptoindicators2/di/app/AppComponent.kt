package com.sweetmay.advancedcryptoindicators2.di.app

import com.sweetmay.advancedcryptoindicators2.di.fav.FavSubComponent
import com.sweetmay.advancedcryptoindicators2.di.fng.FnGSubComponent
import com.sweetmay.advancedcryptoindicators2.di.list.ListSubComponent
import com.sweetmay.advancedcryptoindicators2.di.modules.ApiModule
import com.sweetmay.advancedcryptoindicators2.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class
])
interface AppComponent {
    fun listSubComponent(): ListSubComponent
    fun fngComponent(): FnGSubComponent
    fun favComponent(): FavSubComponent
}