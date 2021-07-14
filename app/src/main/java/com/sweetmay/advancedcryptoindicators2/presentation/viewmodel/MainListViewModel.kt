package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel

import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.App.Companion.pagingConfig
import com.sweetmay.advancedcryptoindicators2.domain.*
import com.sweetmay.advancedcryptoindicators2.model.db.cache.room.FavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.base.BaseViewModel
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CoinsListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.presenter.list.CoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.utils.PagingState
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoader
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import kotlinx.coroutines.launch

class MainListViewModel : BaseViewModel<List<CoinView>>(),
  CoinsListPresenterCallbacks {

  private val pagingState = PagingState(pagingConfig = pagingConfig)
  private val converter = Converter()
  private val imageLoader = GlideImageLoader()
  private val coinsRepo: ICoinsListRepo =
    CoinsListRepo(ApiHolderCoinGecko(App.instance.BASE_URL_CG), pagingConfig)
  private val favCoinsCache = FavCoinsCache(App.instance.favCoinsDao)

  private val getCoinsListUseCase: IGetCoinsListUseCase =
    GetCoinsListUseCase(coinsRepo, favCoinsCache, this, App.settings)
  private val saveToFavUseCase: ISaveToFavUseCase = SaveToFavUseCase(favCoinsCache)
  private val deleteFromFavUseCase: IDeleteFromFavUseCase = DeleteFromFavUseCase(favCoinsCache)

  private val coinsListPresenter = CoinsListPresenter(this, converter, pagingState)

  init {
    loadData()
  }

  override fun loadData() {
    _uiState.value = MainListViewState.Loading
    pagingState.loading = true
    launch {
      _uiState.value =
        getCoinsListUseCase.getCoins(pagingState)
    }
    pagingState.loading = false
  }

  fun addCoinsToAdapter(value: List<CoinView>) {
    coinsListPresenter.coins.addAll(value)
  }

  fun createAdapter(): CoinsListAdapter {
    return CoinsListAdapter(coinsListPresenter, imageLoader)
  }

  override fun navigateToDetailedScreen(coinView: CoinView) {
    TODO("Not yet implemented")
  }

  override fun saveToCache(coinView: CoinView) {
    launch {
      saveToFavUseCase.saveToFav(coinView)
    }
  }

  override fun deleteFromCache(coinView: CoinView) {
    launch {
      deleteFromFavUseCase.deleteFromFav(coinView)
    }
  }

}