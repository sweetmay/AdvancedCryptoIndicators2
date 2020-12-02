package com.sweetmay.advancedcryptoindicators2

import android.app.Application

class App: Application() {

    companion object{
        const val BASE_URL = "https://api.coingecko.com/"
        lateinit var instance: App
        private set



    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}