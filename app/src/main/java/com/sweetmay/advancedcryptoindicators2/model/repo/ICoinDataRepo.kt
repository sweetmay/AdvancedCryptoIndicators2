package com.sweetmay.advancedcryptoindicators2.model.repo

import com.sweetmay.advancedcryptoindicators2.model.entity.Coin
import io.reactivex.rxjava3.core.Single

interface ICoinDataRepo {
    fun getCoin(): Single<Coin>
}