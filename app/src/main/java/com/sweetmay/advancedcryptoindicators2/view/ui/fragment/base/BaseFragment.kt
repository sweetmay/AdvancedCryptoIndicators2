package com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.view.base.BaseView
import moxy.MvpAppCompatFragment

abstract class BaseFragment<VB: ViewBinding>: MvpAppCompatFragment(), BaseView {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = setBinding(inflater, container)
        return _binding!!.root
    }

    abstract fun setBinding(inflater: LayoutInflater, container: ViewGroup?): VB?

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun renderError(e: Exception) {
        val snackbar = Snackbar.make(binding.root, R.string.error_data_load, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("retry"){
            onErrorHandleClick()
        }
        snackbar.show()
    }

    abstract fun onErrorHandleClick()
}