package com.sweetmay.advancedcryptoindicators2.presenter.list

import android.widget.ImageButton
import com.sweetmay.advancedcryptoindicators2.view.custom.FavButton
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView

interface ICoinsListPresenter: IListPresenter<CoinItemView> {
    fun saveFavCoin(view: CoinItemView)
    fun deleteFavCoin(view: CoinItemView)
}