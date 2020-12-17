package com.sweetmay.advancedcryptoindicators2.model.db.dao

import androidx.room.*
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.GeneralInfoCoinDb

@Dao
interface CoinsDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coin: CoinDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coins: List<CoinDb>)

    @Delete
    fun delete(coin: CoinDb)

    @Delete
    fun delete(coins: List<CoinDb>)

    @Query("SELECT * FROM CoinDb")
    fun getAll(): List<CoinDb>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(coins: List<GeneralInfoCoinDb>)

    @Query("DELETE FROM GeneralInfoCoinDb")
    fun deleteAll()

    @Query("SELECT * FROM GeneralInfoCoinDb")
    fun getFullList(): List<GeneralInfoCoinDb>

    @Query("SELECT * FROM GeneralInfoCoinDb WHERE name LIKE '%' || :search || '%'")
    fun findById(search: String): List<GeneralInfoCoinDb>
}