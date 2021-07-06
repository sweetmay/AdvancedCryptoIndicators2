package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.TrendingCoinsFragmentBinding
import com.sweetmay.advancedcryptoindicators2.presenter.callback.CallBackToChangeTheme
import com.sweetmay.advancedcryptoindicators2.vm.TrendingCoinsViewModel

class TrendingCoinsFragment: Fragment() {

  private lateinit var binding: TrendingCoinsFragmentBinding
  private lateinit var callBackToChangeTheme: CallBackToChangeTheme

  private val viewModel: TrendingCoinsViewModel
  by viewModels { TrendingCoinsViewModel.Factory(App.injection, findNavController()) }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    callBackToChangeTheme = context as CallBackToChangeTheme
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = TrendingCoinsFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    inflateToolbar(binding.toolbarInclude.toolbar, R.menu.options_menu_main)
    initRv()
    binding.toolbarInclude.toolbar.title = getString(R.string.trending_coins)
    viewModel.coins.observe(viewLifecycleOwner, {
      binding.coinRv.adapter?.notifyDataSetChanged()
      binding.toolbarInclude.progressBar.hide()
    })
  }

  fun initRv() {
    with(binding.coinRv){
      layoutManager = LinearLayoutManager(context)
      adapter = viewModel.createAdapter()
    }
  }

  fun inflateToolbar(toolbar: Toolbar, menuRes: Int){
    toolbar.inflateMenu(menuRes)
    toolbar.menu.findItem(R.id.night_mode).setOnMenuItemClickListener {
      callBackToChangeTheme.toggleTheme()
      true
    }
  }
}