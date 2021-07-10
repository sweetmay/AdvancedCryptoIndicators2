package com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.base.BaseViewModel
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CallBackToChangeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<VB : ViewBinding, T> : Fragment(), CoroutineScope {

  override val coroutineContext: CoroutineContext by lazy {
    Dispatchers.Main + Job()
  }

  abstract val viewModel: BaseViewModel<T>
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

  open fun inflateToolbar(toolbar: Toolbar, menuRes: Int) {
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
}