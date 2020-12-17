package com.sweetmay.advancedcryptoindicators2.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sweetmay.advancedcryptoindicators2.model.db.dao.CoinsDbDao
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.GeneralInfoCoinDb

@Database(entities = [CoinDb::class, GeneralInfoCoinDb::class], version = 1)

abstract class DataBase : RoomDatabase() {
    abstract fun getDao(): CoinsDbDao
}