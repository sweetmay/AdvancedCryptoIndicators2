package com.sweetmay.advancedcryptoindicators2.model.repo

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CoinDetailed
import io.reactivex.rxjava3.core.Single

interface ICoinDataRepo {
    fun getCoin(coinBase: CoinBase): Single<CoinDetailed>
}