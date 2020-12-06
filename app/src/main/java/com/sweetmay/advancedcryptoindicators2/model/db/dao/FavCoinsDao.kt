package com.sweetmay.advancedcryptoindicators2.model.db.dao

import androidx.room.*
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb

@Dao
interface FavCoinsDao {
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

}