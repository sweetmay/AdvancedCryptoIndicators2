package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel

import android.widget.ImageView
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.App.Companion.pagingConfig
import com.sweetmay.advancedcryptoindicators2.App.Companion.settings
import com.sweetmay.advancedcryptoindicators2.data.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.data.db.cache.room.FavCoinsCache
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.data.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.data.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.domain.usecase.DeleteFromFavUseCase
import com.sweetmay.advancedcryptoindicators2.domain.usecase.GetFavCoinsUseCase
import com.sweetmay.advancedcryptoindicators2.domain.usecase.IDeleteFromFavUseCase
import com.sweetmay.advancedcryptoindicators2.domain.usecase.IGetFavCoinsUseCase
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.base.BaseViewModel
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.FavViewState
import com.sweetmay.advancedcryptoindicators2.presentation.callback.FavListCallBacks
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoader
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoader
import com.sweetmay.advancedcryptoindicators2.presentation.adapter.FavListAdapter
import kotlinx.coroutines.launch

class FavViewModel: BaseViewModel<List<CoinView>>(), FavListCallBacks {

  private val favCache: IFavCoinsCache = FavCoinsCache(App.instance.favCoinsDao)
  private val coinsRepo: ICoinsListRepo =
      CoinsListRepo(ApiHolderCoinGecko(App.instance.BASE_URL_CG), pagingConfig)
  private val converter = Converter()
  private val imageLoader: IImageLoader<ImageView> = GlideImageLoader()

  private val getFavCoinsUseCase: IGetFavCoinsUseCase = GetFavCoinsUseCase(favCache, coinsRepo, settings, converter)
  private val deleteFromFavUseCase: IDeleteFromFavUseCase = DeleteFromFavUseCase(favCache)

  val adapter = FavListAdapter(imageLoader, converter, this)

  override fun notifyElementRemoved(pos: Int) {
    if(adapter.coins.size == 0){
      _uiState.value = FavViewState.Empty
      return
    }
    _uiState.value = FavViewState.RemovingElement(pos)
  }

  override fun deleteFav(coinView: CoinView) {
    launch {
      deleteFromFavUseCase.deleteFromFav(coinView)
    }
  }

  fun loadData() {
    adapter.coins.clear()
    _uiState.value = FavViewState.Loading
    launch {
      _uiState.value = getFavCoinsUseCase.getFavCoins()
    }
  }

  override fun onItemClick(coinView: CoinView) {
    TODO("Not yet implemented")
  }

}