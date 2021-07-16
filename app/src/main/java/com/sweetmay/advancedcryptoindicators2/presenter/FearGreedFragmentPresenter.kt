//package com.sweetmay.advancedcryptoindicators2.presenter
//
//import com.sweetmay.advancedcryptoindicators2.IAppInjection
//import com.sweetmay.advancedcryptoindicators2.data.repo.ResultWrapper
//import com.sweetmay.advancedcryptoindicators2.data.repo.IFnGRepo
//import com.sweetmay.advancedcryptoindicators2.presentation.view.FnGView
//import kotlinx.coroutines.*
//import moxy.MvpPresenter
//import java.lang.Exception
//import javax.inject.Inject
//
//class FearGreedFragmentPresenter(private val injection: IAppInjection) : MvpPresenter<FnGView>() {
//
//  init {
//    injection.initFngComponent()?.inject(this)
//  }
//
//  @Inject
//  lateinit var fngRepo: IFnGRepo
//  private val scope = CoroutineScope(Dispatchers.IO)
//
//  override fun onFirstViewAttach() {
//    super.onFirstViewAttach()
//    setTitle()
//    loadData()
//  }
//
//  fun loadData() {
//    viewState.showLoading()
//    scope.launch {
//      val result = fngRepo.getFng("31")
//      withContext(Dispatchers.Main) {
//        when(result){
//          is ResultWrapper.Success-> viewState.showData(result.value)
//          is ResultWrapper.Error-> viewState.renderError(Exception(result.errorMsg))
//        }
//        viewState.hideLoading()
//      }
//    }
//
//  }
//
//  private fun setTitle() {
//    viewState.setTitle()
//  }
//
//  override fun onDestroy() {
//    super.onDestroy()
//    scope.cancel()
//    injection.releaseFngSubComponent()
//  }
//}