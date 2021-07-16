package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel

import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.App.Companion.pagingConfig
import com.sweetmay.advancedcryptoindicators2.data.db.cache.room.FavCoinsCache
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.data.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.data.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.domain.usecase.*
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.base.BaseViewModel
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.MainListViewState
import com.sweetmay.advancedcryptoindicators2.presentation.callback.MainListCallBacks
import com.sweetmay.advancedcryptoindicators2.utils.PagingState
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoader
import com.sweetmay.advancedcryptoindicators2.presentation.adapter.MainListAdapter
import kotlinx.coroutines.launch

class MainListViewModel : BaseViewModel<List<CoinView>>(),
  MainListCallBacks {


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
  val adapter: MainListAdapter = MainListAdapter(imageLoader, converter, this)

  init {
    loadData()
  }

  override fun saveFav(coinView: CoinView) {
    launch {
      saveToFavUseCase.saveToFav(coinView)
    }
  }

  override fun deleteFav(coinView: CoinView) {
    launch {
      deleteFromFavUseCase.deleteFromFav(coinView)
    }
  }

  override fun scrollToLastPos() {
    if(pagingState.firstItem != 1){
      _uiState.value = MainListViewState.ScrollingToLastPos(pagingState.firstItem)
    }
  }

  override fun updatePagingState(firstItem: Int, lastItem: Int) {
    pagingState.apply {
      this.firstItem = firstItem
      this.lastItem = lastItem
    }
    if (!pagingState.loading && lastItem >= adapter.coins.size - pagingState.pagingConfig.pageThreshold) {
      pagingState.page++
      pagingState.loading = true
      loadData()
      return
    }
  }

  private fun loadData() {
    _uiState.value = MainListViewState.Loading

    launch {
      _uiState.value =
        getCoinsListUseCase.getCoins(pagingState)
      pagingState.loading = false
    }
  }

  override fun onItemClick(coinView: CoinView) {
    TODO("Not yet implemented")
  }

  fun addCoinsToAdapter(value: List<CoinView>) {
    adapter.coins.addAll(value)
  }


}