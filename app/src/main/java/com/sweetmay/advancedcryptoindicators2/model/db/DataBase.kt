package com.sweetmay.advancedcryptoindicators2.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sweetmay.advancedcryptoindicators2.model.db.dao.FavCoinsDao
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb

@Database(entities = [CoinDb::class], version = 1)

abstract class DataBase : RoomDatabase() {
    abstract fun getDao(): FavCoinsDao
}