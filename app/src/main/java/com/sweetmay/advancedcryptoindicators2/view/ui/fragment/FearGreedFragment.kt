package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.FearGreedFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.entity.fng.FnGEntity
import com.sweetmay.advancedcryptoindicators2.presenter.FearGreedFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.view.FnGView
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment
import moxy.ktx.moxyPresenter

class FearGreedFragment: BaseFragment<FearGreedFragmentBinding>(), FnGView{

    lateinit var navController: NavController
    private val fragmentPresenter: FearGreedFragmentPresenter by moxyPresenter {
        FearGreedFragmentPresenter(App.injection)
    }


    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FearGreedFragmentBinding {
        navController = findNavController()
        return FearGreedFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentPresenter.loadData()
    }

    override fun onErrorHandleClick() {
        fragmentPresenter.loadData()
    }

    override fun setTitle() {
        binding.toolbarInclude.toolbar.title = getString(R.string.fear_greed_title)
    }

    override fun showLoading() {
        binding.toolbarInclude.progressBar.show()
    }

    override fun hideLoading() {
        binding.toolbarInclude.progressBar.hide()
    }


    override fun showData(fng: FnGEntity) {
        val data = fng.data
        animateFnGView(data[0].value.toInt())
        with(binding){
            textNowFngStatus.text = data[0].value_classification

            valueNow.text = data[0].value

            textYesterdayFngStatus.text = data[1].value_classification

            valueYesterday.text = data[1].value

            textLastWeekFngStatus.text = data[6].value_classification

            valueLastWeek.text = data[6].value

            textLastMonthFngStatus.text = data[30].value_classification

            valueLastMonth.text = data[30].value
        }
    }

    private fun animateFnGView(fng: Int) {
        with(binding.fearGreedView){
            doOnLayout {
                this.rotateToAnimation(fng)
            }
        }
    }

}