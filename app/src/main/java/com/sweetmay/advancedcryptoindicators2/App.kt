package com.sweetmay.advancedcryptoindicators2

import android.app.Application
import androidx.room.Room
import com.sweetmay.advancedcryptoindicators2.model.db.DataBase
import com.sweetmay.advancedcryptoindicators2.model.db.dao.CoinsGeneralInfoDao
import com.sweetmay.advancedcryptoindicators2.model.db.dao.FavCoinsDao

class App: Application() {

    lateinit var favCoinsDao: FavCoinsDao
    lateinit var generalInfoDao: CoinsGeneralInfoDao

    val THEME_KEY = "theme"
    val SETTINGS = "prefs"
    val BASE_URL_CG = "https://api.coingecko.com/"
    val BASE_URL_FNG = "https://api.alternative.me/"

    companion object{
        lateinit var instance: App
        private set

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
        val db = Room.databaseBuilder(
                applicationContext,
                DataBase::class.java,
                "CoinDb"
        ).build()

        with(db){
            favCoinsDao = favCoinsDao()
            generalInfoDao = generalInfoDao()
        }
    }
}