package com.sweetmay.advancedcryptoindicators2.di.modules

import com.sweetmay.advancedcryptoindicators2.model.settings.ISettings
import com.sweetmay.advancedcryptoindicators2.model.settings.Settings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SettingsModule {
    @Singleton
    @Provides

    fun settings(): ISettings{
        return Settings()
    }
}