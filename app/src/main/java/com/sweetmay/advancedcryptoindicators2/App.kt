package com.sweetmay.advancedcryptoindicators2

import android.app.Application
import androidx.room.Room
import com.sweetmay.advancedcryptoindicators2.model.db.DataBase
import com.sweetmay.advancedcryptoindicators2.model.db.dao.FavCoinsDao

class App: Application() {

    lateinit var dao: FavCoinsDao

    companion object{
        const val BASE_URL = "https://api.coingecko.com/"
        lateinit var instance: App
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        dao = Room.databaseBuilder(
                applicationContext,
                DataBase::class.java,
                "CoinDb")
                .build()
                .getDao()
    }
}