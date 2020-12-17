package com.sweetmay.advancedcryptoindicators2

import android.app.Application
import androidx.room.Room
import com.sweetmay.advancedcryptoindicators2.model.db.DataBase
import com.sweetmay.advancedcryptoindicators2.model.db.dao.CoinsDbDao

class App: Application() {

    lateinit var dbDao: CoinsDbDao
    val BASE_URL_CG = "https://api.coingecko.com/"
    val BASE_URL_FNG = "https://api.alternative.me/"


    companion object{
        lateinit var instance: App
        lateinit var injection: IAppInjection
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDb()
        initInjection()
    }

    private fun initInjection() {
        injection = AppInjection(this)
    }

    private fun initDb() {
        dbDao = Room.databaseBuilder(
            applicationContext,
            DataBase::class.java,
            "CoinDb"
        ).build()
                .getDao()
    }
}