package com.sweetmay.advancedcryptoindicators2

import android.app.Application
import androidx.room.Room
import com.sweetmay.advancedcryptoindicators2.data.db.DataBase
import com.sweetmay.advancedcryptoindicators2.data.db.dao.CoinsGeneralInfoDao
import com.sweetmay.advancedcryptoindicators2.data.db.dao.FavCoinsDao
import com.sweetmay.advancedcryptoindicators2.data.settings.ISettings
import com.sweetmay.advancedcryptoindicators2.data.settings.Settings
import com.sweetmay.advancedcryptoindicators2.utils.PagingConfig

class App : Application() {

  lateinit var favCoinsDao: FavCoinsDao
  lateinit var generalInfoDao: CoinsGeneralInfoDao

  val THEME_KEY = "theme"
  val SETTINGS = "prefs"
  val BASE_URL_CG = "https://api.coingecko.com/"
  val BASE_URL_FNG = "https://api.alternative.me/"

  companion object {
    lateinit var instance: App
      private set

    lateinit var pagingConfig: PagingConfig
    lateinit var settings: ISettings
//        lateinit var injection: IAppInjection
//        private set
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
    initDb()
    pagingConfig = PagingConfig(20, 100)
    settings = Settings(getSharedPreferences(SETTINGS, MODE_PRIVATE), this)
//        initInjection()
  }

  private fun initInjection() {
//        injection = AppInjection(this)
  }

  private fun initDb() {
    val db = Room.databaseBuilder(
        applicationContext,
        DataBase::class.java,
        "CoinDb"
    ).build()

    with(db) {
      favCoinsDao = favCoinsDao()
      generalInfoDao = generalInfoDao()
    }
  }
}