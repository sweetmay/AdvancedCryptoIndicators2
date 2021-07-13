package com.sweetmay.advancedcryptoindicators2.presenter.list

import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CoinsListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.utils.PagingState
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView
import java.util.*

open class CoinsListPresenter(
  private val callback: CoinsListPresenterCallbacks,
  private val converter: Converter,
  val pagingState: PagingState
) : ICoinsListPresenter {

  val coins = LinkedList<CoinView>()

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

  override fun updatePagingState(firstItem: Int, lastItem: Int) {
    pagingState.apply {
      this.firstItem = firstItem
      this.lastItem = lastItem
    }
    if(!pagingState.loading && lastItem >= coins.size-pagingState.pagingConfig.pageThreshold){
      pagingState.page++
      callback.loadData()
      return
    }

  }

  override fun scrollToLastPos(rv: RecyclerView) {
    rv.scrollToPosition(pagingState.firstItem)
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