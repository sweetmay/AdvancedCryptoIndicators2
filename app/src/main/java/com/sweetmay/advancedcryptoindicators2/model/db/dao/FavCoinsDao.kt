package com.sweetmay.advancedcryptoindicators2.model.db.dao

import androidx.room.*
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper

@Dao
interface FavCoinsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coin: CoinDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coins: List<CoinDb>)

    @Delete
    suspend fun delete(coin: CoinDb)

    @Delete
    suspend fun delete(coins: List<CoinDb>)

    @Query("SELECT * FROM CoinDb")
    suspend fun getAll(): List<CoinDb>
}