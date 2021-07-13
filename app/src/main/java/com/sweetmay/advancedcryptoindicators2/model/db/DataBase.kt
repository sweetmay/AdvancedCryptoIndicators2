package com.sweetmay.advancedcryptoindicators2.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sweetmay.advancedcryptoindicators2.model.db.dao.CoinsGeneralInfoDao
import com.sweetmay.advancedcryptoindicators2.model.db.dao.FavCoinsDao
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.db.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.db.GeneralInfoCoinDb

@Database(entities = [CoinDb::class, GeneralInfoCoinDb::class], version = 1)

abstract class DataBase : RoomDatabase() {
    abstract fun favCoinsDao(): FavCoinsDao
    abstract fun generalInfoDao(): CoinsGeneralInfoDao
}