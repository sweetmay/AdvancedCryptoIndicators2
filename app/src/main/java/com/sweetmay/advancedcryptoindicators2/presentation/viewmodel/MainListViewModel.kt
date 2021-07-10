package com.sweetmay.advancedcryptoindicators2.presentation.viewmodel

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.domain.GetCoinsListUseCase
import com.sweetmay.advancedcryptoindicators2.domain.IGetCoinsListUseCase
import com.sweetmay.advancedcryptoindicators2.model.db.cache.room.FavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.IFnGRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.ResultWrapper
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.FnGRepo
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.base.BaseViewModel
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CoinsListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.presenter.list.CoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderCoinGecko
import com.sweetmay.advancedcryptoindicators2.utils.apiholder.ApiHolderFnG
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoader
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainListViewModel: BaseViewModel<List<CoinBase>>(), CoinsListPresenterCallbacks {

  private val converter = Converter()
  private val imageLoader = GlideImageLoader()
  private val coinsRepo: ICoinsListRepo = CoinsListRepo(ApiHolderCoinGecko(App.instance.BASE_URL_CG))
  private val favCoinsCache = FavCoinsCache(App.instance.favCoinsDao)
  private val getCoinsListUseCase: IGetCoinsListUseCase = GetCoinsListUseCase(coinsRepo, favCoinsCache, this)
  private val coinsListPresenter = CoinsListPresenter(this, converter)

  init {
    launch {
      _uiState.value = getCoinsListUseCase.invoke()
    }
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

  fun addCoinsToAdapter(value: List<CoinBase>) {
    coinsListPresenter.coins.addAll(value)
  }

}