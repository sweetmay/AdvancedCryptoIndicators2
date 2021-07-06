package com.sweetmay.advancedcryptoindicators2.vm

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.sweetmay.advancedcryptoindicators2.IAppInjection
import com.sweetmay.advancedcryptoindicators2.model.db.cache.IFavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.settings.ISettings
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CoinsListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.presenter.list.CoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoader
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.TrendingCoinsFragmentDirections
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class TrendingCoinsViewModel(
  private val injection: IAppInjection,
  private val navController: NavController
) : ViewModel(), CoinsListPresenterCallbacks {

  private val _coins = MutableLiveData<List<CoinBase>>()
  val coins: LiveData<List<CoinBase>>
    get() = _coins

  init {
    injection.initListComponent()?.inject(this)
    loadData()
  }

  @Inject
  lateinit var coinsRepo: ICoinsListRepo

  @Inject
  lateinit var scheduler: Scheduler

  @Inject
  lateinit var favCache: IFavCoinsCache

  @Inject
  lateinit var imageLoader: IImageLoader<ImageView>

  @Inject
  lateinit var converter: Converter

  @Inject
  lateinit var settings: ISettings

  private val coinsListPresenter = CoinsListPresenter(this, converter)


  fun createAdapter(): CoinsListAdapter {
    return CoinsListAdapter(coinsListPresenter, imageLoader)
  }

  fun loadData(currencyAgainst: String = settings.currencyAgainst) {
    coinsRepo.getTrending(currencyAgainst, order = settings.order)
      .observeOn(scheduler).subscribe({ list ->
        coinsListPresenter.coins.addAll(list)
        _coins.postValue(list)
      }, {

      })
  }

  override fun navigateToDetailedScreen(coinBase: CoinBase) {
    val action =
      TrendingCoinsFragmentDirections.actionTrendingCoinsFragmentToCoinDataFragment(coinBase)
    navController.navigate(action)
  }

  override fun saveToCache(coinBase: CoinBase) {
    favCache.saveFavCoin(coinBase).subscribe()
  }

  override fun deleteFromCache(coinBase: CoinBase) {
    favCache.deleteFavCoin(coinBase).subscribe()
  }

  override fun onCleared() {
    super.onCleared()
    injection.releaseListComponent()
  }

  class Factory(
    private val injection: IAppInjection,
    private val navController: NavController
  ) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return TrendingCoinsViewModel(injection, navController) as T
    }
  }
}