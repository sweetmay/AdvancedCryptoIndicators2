package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.FearGreedFragmentBinding
import com.sweetmay.advancedcryptoindicators2.presenter.FearGreedPresenter
import com.sweetmay.advancedcryptoindicators2.view.FnGView
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment
import moxy.ktx.moxyPresenter

class FearGreedFragment: BaseFragment<FearGreedFragmentBinding>(), FnGView{

    lateinit var navController: NavController
    private val presenter: FearGreedPresenter by moxyPresenter { FearGreedPresenter() }


    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FearGreedFragmentBinding? {
        navController = findNavController()
        return FearGreedFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadData()
    }

    override fun onErrorHandleClick() {
        presenter.loadData()
    }

    override fun setTitle() {
        binding.toolbarInclude.toolbar.title = getString(R.string.fear_greed_title)
    }


    override fun showData(fng: Int) {
        with(binding.fearGreedView){
            doOnLayout {
                this.rotateToAnimation(fng)
            }
        }
    }

    override fun renderError(msg: String) {

    }

}