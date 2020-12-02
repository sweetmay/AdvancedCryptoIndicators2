package com.sweetmay.advancedcryptoindicators2.model.repo.retrofit

import com.sweetmay.advancedcryptoindicators2.model.entity.Coin
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinDataRepo
import io.reactivex.rxjava3.core.Single

class CoinDataRepo: ICoinDataRepo {
    override fun getCoin(): Single<Coin> {
        TODO("Not yet implemented")
    }
}