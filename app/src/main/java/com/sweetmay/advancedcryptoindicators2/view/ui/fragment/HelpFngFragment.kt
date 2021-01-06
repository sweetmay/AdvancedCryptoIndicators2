package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.HelpFragmentFngBinding
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment

class HelpFngFragment: BaseFragment<HelpFragmentFngBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initLink()
    }

    private fun initToolbar() {
        with(binding.toolbarInclude) {
            progressBar.hide()
            toolbar.setTitle(R.string.help)
            toolbar.setNavigationIcon(R.drawable.navigate_before_24px)
            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun initLink() {
        Linkify.addLinks(binding.fngHelpBody, Linkify.WEB_URLS)
        binding.fngHelpBody.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HelpFragmentFngBinding {
        return HelpFragmentFngBinding.inflate(inflater, container, false)
    }

    override fun onErrorHandleClick() {

    }
}