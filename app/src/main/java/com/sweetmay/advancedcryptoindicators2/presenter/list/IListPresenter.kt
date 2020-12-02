package com.sweetmay.advancedcryptoindicators2.presenter.list

import com.sweetmay.advancedcryptoindicators2.view.item.IItemView

interface IListPresenter<V: IItemView> {
    fun onItemClick(view: V)
    fun bindView(view: V)
    fun getCount(): Int
}