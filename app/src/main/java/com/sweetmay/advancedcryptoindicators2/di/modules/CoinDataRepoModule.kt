//package com.sweetmay.advancedcryptoindicators2.di.modules
//
//import com.sweetmay.advancedcryptoindicators2.di.detailed.CoinDetailedSubComponentScope
//import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinDataRepo
//import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinDataRepo
//import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko
//import dagger.Module
//import dagger.Provides
//
//@Module
//class CoinDataRepoModule {
//
//    @CoinDetailedSubComponentScope
//    @Provides
//    fun repo(apiHolderCoinGecko: ApiHolderCoinGecko): ICoinDataRepo {
//        return CoinDataRepo(apiHolderCoinGecko)
//    }
//}