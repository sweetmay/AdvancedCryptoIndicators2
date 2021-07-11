package com.sweetmay.advancedcryptoindicators2.presenter.list

import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CoinsListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.utils.PagingConfig
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView

open class CoinsListPresenter(
  private val callback: CoinsListPresenterCallbacks,
  private val converter: Converter, private val pagingConfig: PagingConfig
) : ICoinsListPresenter {

  val coins = ArrayList<CoinBase>()

  override fun onFavButtonClicked(view: CoinItemView, state: Boolean) {
    coins[view.getPos()].is_favorite = state
  }

  override fun saveFav(view: CoinItemView) {
    coins[view.getPos()].is_favorite = true
    callback.saveToCache(coins[view.getPos()])
  }

  override fun deleteFav(view: CoinItemView) {
    coins[view.getPos()].is_favorite = false
    callback.deleteFromCache(coins[view.getPos()])
  }

  override fun loadNextPage(currentBindingPosition: Int) {
    if(coins.size - currentBindingPosition <= pagingConfig.pageThreshold){
      callback.loadMore(coins.size / pagingConfig.perPageLimit + 1)
    }
  }

  override fun onItemClick(view: CoinItemView) {
    callback.navigateToDetailedScreen((coins[view.getPos()]))
  }


  override fun bindView(view: CoinItemView) {
    with(view) {
      setIcon(coins[view.getPos()].image)
      setName(coins[view.getPos()].name)
      setPrice(coins[view.getPos()].current_price)
      setFavIcon(coins[view.getPos()].is_favorite)
      setPriceChange(
        converter
          .convertChange(coins[view.getPos()].price_change_percentage_24h)
      )
    }
  }

  override fun getCount(): Int {
    return coins.size
  }

}