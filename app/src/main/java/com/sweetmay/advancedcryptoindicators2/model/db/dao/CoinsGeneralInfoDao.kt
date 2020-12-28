package com.sweetmay.advancedcryptoindicators2.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.GeneralInfoCoinDb

@Dao
interface CoinsGeneralInfoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(coins: List<GeneralInfoCoinDb>)

    @Query("DELETE FROM GeneralInfoCoinDb")
    fun deleteAll()

    @Query("SELECT * FROM GeneralInfoCoinDb")
    fun getFullList(): List<GeneralInfoCoinDb>

    @Query("SELECT * FROM GeneralInfoCoinDb WHERE name LIKE '%' || :search || '%'")
    fun fundByName(search: String): List<GeneralInfoCoinDb>
}