package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel

import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.App.Companion.pagingConfig
import com.sweetmay.advancedcryptoindicators2.App.Companion.settings
import com.sweetmay.advancedcryptoindicators2.domain.*
import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.db.cache.room.FavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.base.BaseViewModel
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.FavViewState
import com.sweetmay.advancedcryptoindicators2.presenter.callback.FavListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.presenter.list.FavCoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import kotlinx.coroutines.launch

class FavViewModel: BaseViewModel<List<CoinView>>(), FavListPresenterCallbacks {

  private val favCache: IFavCoinsCache = FavCoinsCache(App.instance.favCoinsDao)
  private val coinsRepo: ICoinsListRepo =
      CoinsListRepo(ApiHolderCoinGecko(App.instance.BASE_URL_CG), pagingConfig)
  private val favCoinsCache = FavCoinsCache(App.instance.favCoinsDao)
  private val converter = Converter()

  private val getFavCoinsUseCase: IGetFavCoinsUseCase = GetFavCoinsUseCase(favCache, coinsRepo, settings, converter)
  private val deleteFromFavUseCase: IDeleteFromFavUseCase = DeleteFromFavUseCase(favCache)

  private val favCoinsListPresenter = FavCoinsListPresenter(this, converter, null)

  init {
    _uiState.value = FavViewState.Loading
    launch {

    }
  }


}