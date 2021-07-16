package com.sweetmay.advancedcryptoindicators2.data.db.cache.room

import com.sweetmay.advancedcryptoindicators2.data.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.data.db.dao.FavCoinsDao
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.db.CoinDb
import com.sweetmay.advancedcryptoindicators2.data.repo.ResultWrapper

class FavCoinsCache(private val daoFav: FavCoinsDao): IFavCoinsCache{

    override suspend fun saveFavCoin(coinView: CoinView): ResultWrapper<Unit> {
        return ResultWrapper.Success(daoFav.insert(convert(coinView)))
    }

    override suspend fun getFavCoins(): ResultWrapper<List<CoinDb>> {
        return ResultWrapper.Success(daoFav.getAll())
    }

    override suspend fun deleteFavCoin(coinView: CoinView): ResultWrapper<Unit> {
        return ResultWrapper.Success(daoFav.delete(convert(coinView)))
    }

    private fun convert(coinView: CoinView): CoinDb {
        return CoinDb(coinView.id, coinView.name)
    }


}