package com.sweetmay.advancedcryptoindicators2.presenter

import com.sweetmay.advancedcryptoindicators2.IAppInjection
import com.sweetmay.advancedcryptoindicators2.model.repo.IFnGRepo
import com.sweetmay.advancedcryptoindicators2.view.FnGView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class FearGreedFragmentPresenter(private val injection: IAppInjection): MvpPresenter<FnGView>() {

    init {
        injection.initFngComponent()?.inject(this)
    }

    @Inject
    lateinit var fngRepo: IFnGRepo
    @Inject
    lateinit var scheduler: Scheduler


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setTitle()
        loadData()
    }

    fun loadData() {
        viewState.showLoading()
        fngRepo.getFnG("10").observeOn(scheduler).subscribe ({ fng->
            viewState.showData(fng)
            viewState.hideLoading()
        }, {
            viewState.renderError(it.message?: "Error")
        })

    }

    private fun setTitle() {
        viewState.setTitle()
    }

    override fun onDestroy() {
        super.onDestroy()
        injection.releaseFngSubComponent()
    }
}