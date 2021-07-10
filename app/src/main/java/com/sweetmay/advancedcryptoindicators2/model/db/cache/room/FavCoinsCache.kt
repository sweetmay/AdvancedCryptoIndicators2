package com.sweetmay.advancedcryptoindicators2.model.db.cache.room

import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.dao.FavCoinsDao
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinDb
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class FavCoinsCache(private val daoFav: FavCoinsDao): IFavCoinsCache{

    override suspend fun saveFavCoin(coinBase: CoinBase): ResultWrapper<Unit> {
        return ResultWrapper.Success(daoFav.insert(convert(coinBase)))
    }

    override suspend fun getFavCoins(): ResultWrapper<List<CoinDb>> {
        return ResultWrapper.Success(daoFav.getAll())
    }

    override suspend fun deleteFavCoin(coinBase: CoinBase): ResultWrapper<Unit> {
        return ResultWrapper.Success(daoFav.delete(convert(coinBase)))
    }

    private fun convert(coinBase: CoinBase): CoinDb{
        return CoinDb(coinBase.id, coinBase.name)
    }


}