package com.sweetmay.advancedcryptoindicators2.presenter

import com.sweetmay.advancedcryptoindicators2.view.MainActivityView
import moxy.MvpPresenter

class MainPresenter: MvpPresenter<MainActivityView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initBottomNav()
    }


}