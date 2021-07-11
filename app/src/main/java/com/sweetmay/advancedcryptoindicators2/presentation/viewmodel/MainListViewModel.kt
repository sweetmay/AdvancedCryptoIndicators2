package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel

import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.domain.GetCoinsListUseCase
import com.sweetmay.advancedcryptoindicators2.domain.IGetCoinsListUseCase
import com.sweetmay.advancedcryptoindicators2.model.db.cache.room.FavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.base.BaseViewModel
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CoinsListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.presenter.list.CoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.utils.PagingConfig
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoader
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import kotlinx.coroutines.launch

class MainListViewModel : BaseViewModel<List<CoinBase>>(), CoinsListPresenterCallbacks {
  private val pagingConfig = PagingConfig(20, 100)
  private val converter = Converter()
  private val imageLoader = GlideImageLoader()
  private val coinsRepo: ICoinsListRepo =
    CoinsListRepo(ApiHolderCoinGecko(App.instance.BASE_URL_CG), pagingConfig)
  private val favCoinsCache = FavCoinsCache(App.instance.favCoinsDao)
  private val getCoinsListUseCase: IGetCoinsListUseCase =
    GetCoinsListUseCase(coinsRepo, favCoinsCache, this)
  private val coinsListPresenter = CoinsListPresenter(this, converter, pagingConfig)

  init {
    _uiState.value = ViewState.Loading
    launch {
      loadData()
    }
  }

  private suspend fun loadData(){
    _uiState.value = getCoinsListUseCase.getCoins()
  }

  fun createAdapter(): CoinsListAdapter {
    return CoinsListAdapter(coinsListPresenter, imageLoader)
  }

  override fun navigateToDetailedScreen(coinBase: CoinBase) {
    TODO("Not yet implemented")
  }

  override fun saveToCache(coinBase: CoinBase) {
    TODO("Not yet implemented")
  }

  override fun deleteFromCache(coinBase: CoinBase) {
    TODO("Not yet implemented")
  }

  override fun loadMore(pageToLoad: Int) {
    _uiState.value = ViewState.Loading
    launch {
      _uiState.value = getCoinsListUseCase.loadMore(pageToLoad)
    }
  }

  fun addCoinsToAdapter(value: List<CoinBase>) {
    coinsListPresenter.coins.addAll(value)
  }

}