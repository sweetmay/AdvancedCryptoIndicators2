package com.sweetmay.advancedcryptoindicators2.presenter.list

import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView

interface ICoinsListPresenter: IListPresenter<CoinItemView> {
    fun onFavButtonClicked(view: CoinItemView, state: Boolean)
    fun saveFav(view: CoinItemView)
    fun deleteFav(view: CoinItemView)
    fun updatePagingState(firstItem: Int, lastItem: Int)
    fun scrollToLastPos(rv: RecyclerView)
}