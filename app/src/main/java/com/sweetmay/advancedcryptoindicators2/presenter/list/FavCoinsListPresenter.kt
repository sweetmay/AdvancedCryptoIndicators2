package com.sweetmay.advancedcryptoindicators2.presenter.list

import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.presenter.callback.FavListPresenterCallbacks
import com.sweetmay.advancedcryptoindicators2.utils.PagingState
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView

class FavCoinsListPresenter(
  private val callback: FavListPresenterCallbacks,
  val converter: Converter, pagingState: PagingState?
) : CoinsListPresenter(callback, converter, pagingState) {

  override fun updatePagingState(firstItem: Int, lastItem: Int) {
    //
  }

  override fun scrollToLastPos(rv: RecyclerView) {
    //
  }

  override fun saveFav(view: CoinItemView) {
    //
  }

  override fun deleteFav(view: CoinItemView) {
    coins[view.getPos()].is_favorite = false
    callback.deleteFromCache(coins[view.getPos()])
    callback.notifyElementChange(view.getPos())
  }

}