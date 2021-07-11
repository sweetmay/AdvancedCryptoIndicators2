package com.sweetmay.advancedcryptoindicators2.presenter.list

import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView

interface ICoinsListPresenter: IListPresenter<CoinItemView> {
    fun onFavButtonClicked(view: CoinItemView, state: Boolean)
    fun saveFav(view: CoinItemView)
    fun deleteFav(view: CoinItemView)
    fun loadNextPage(currentBindingPosition: Int)
}