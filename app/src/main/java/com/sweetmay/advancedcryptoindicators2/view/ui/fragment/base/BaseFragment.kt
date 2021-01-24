package com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CallBackToChangeTheme
import com.sweetmay.advancedcryptoindicators2.view.base.BaseView
import moxy.MvpAppCompatFragment

abstract class BaseFragment<VB: ViewBinding>: MvpAppCompatFragment(), BaseView {

    private var _binding: VB? = null
    val binding get() = _binding!!

    lateinit var callBackToChangeTheme: CallBackToChangeTheme

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBackToChangeTheme = context as CallBackToChangeTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = setBinding(inflater, container)
        return _binding!!.root
    }

    abstract fun setBinding(inflater: LayoutInflater, container: ViewGroup?): VB?

    open fun inflateToolbar(toolbar: Toolbar, menuRes: Int){
        toolbar.inflateMenu(menuRes)
        toolbar.menu.findItem(R.id.night_mode).setOnMenuItemClickListener {
            callBackToChangeTheme.toggleTheme()
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun renderError(e: Exception) {
        val snackBar = Snackbar.make(
            binding.root, R.string.error_data_load, Snackbar.LENGTH_INDEFINITE
        )
        snackBar.setAction(getString(R.string.retry)) {
            onErrorHandleClick()
        }
        snackBar.show()
    }

    abstract fun onErrorHandleClick()
}