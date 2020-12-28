package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.HelpFragmentFngBinding
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment

class HelpFngFragment: BaseFragment<HelpFragmentFngBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarInclude.toolbar.setNavigationIcon(R.drawable.navigate_before_24px)
        binding.toolbarInclude.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): HelpFragmentFngBinding? {
        return HelpFragmentFngBinding.inflate(inflater, container, false)
    }

    override fun onErrorHandleClick() {

    }
}