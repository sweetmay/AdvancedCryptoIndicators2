package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.HelpFragmentCoinBinding
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment

class HelpCoinDetailedFragment: BaseFragment<HelpFragmentCoinBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initLink()
        setHelpImage()
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

    private fun setHelpImage() {
        binding.imageHelpStrategy.setImageResource(R.drawable.screen_bounce_arime_help)
    }

    private fun initLink() {
        Linkify.addLinks(binding.sentimentHelpBody, Linkify.WEB_URLS)
        binding.sentimentHelpBody.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onErrorHandleClick() {
        //nothing to do
    }

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): HelpFragmentCoinBinding {
        return HelpFragmentCoinBinding.inflate(inflater, container, false)
    }
}