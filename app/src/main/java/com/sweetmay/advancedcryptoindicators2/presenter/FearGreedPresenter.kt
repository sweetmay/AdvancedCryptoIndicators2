package com.sweetmay.advancedcryptoindicators2.presenter

import com.sweetmay.advancedcryptoindicators2.view.FnGView
import moxy.MvpPresenter

class FearGreedPresenter: MvpPresenter<FnGView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setTitle()
        loadData()
    }

    fun loadData() {
        viewState.showData(90)
    }

    private fun setTitle() {
        viewState.setTitle()
    }

    fun showData(fng: Int){
        viewState.showData(fng)
    }
}