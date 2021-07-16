package com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.ListFragmentBinding
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment.base.BaseFragment
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.MainListViewModel
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.MainListViewState
import kotlinx.coroutines.flow.collect

class ListFragment : BaseFragment<ListFragmentBinding, List<CoinView>>() {

  override val viewModel: MainListViewModel
    by navGraphViewModels(R.id.nav_graph)

  private lateinit var navController: NavController


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    inflateToolbar(binding.toolbarInclude.toolbar, R.menu.options_menu_main)
    navigateToSearch()
    initRv()
    setTitle()
    lifecycleScope.launchWhenCreated {
      viewModel.uiState.collect {
        when (it) {
          is MainListViewState.Loading -> {
            Log.d("AAAAA", "LOADING")
            binding.toolbarInclude.progressBar.show()
          }
          is MainListViewState.Success -> {
            Log.d("AAAAA", "SUCCESS")
            binding.toolbarInclude.progressBar.hide()
            viewModel.addCoinsToAdapter(it.value)
            binding.coinRv.adapter?.notifyDataSetChanged()
          }
          is MainListViewState.Error -> {
            Toast.makeText(requireContext(), it.errorMsg, Toast.LENGTH_LONG).show()
          }
          is MainListViewState.ScrollingToLastPos -> {
            binding.toolbarInclude.progressBar.hide()
            binding.coinRv.scrollToPosition(it.pos)
          }
          else -> {
            Log.d("AAAAA", "ELSE")
          }
        }
      }
    }
  }

  private fun navigateToSearch() {
//    with(binding.toolbarInclude.toolbar) {
//      menu.findItem(R.id.search_button_menu).setOnMenuItemClickListener {
//        navController.navigate(ListFragmentDirections.actionListFragmentToSearchFragment())
//        true
//      }
//    }
  }


  private fun initRv() {
    with(binding.coinRv) {
      layoutManager = LinearLayoutManager(context)
      adapter = viewModel.adapter
    }
  }

  override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): ListFragmentBinding? {
    navController = findNavController()
    return ListFragmentBinding.inflate(inflater, container, false)
  }


  private fun setTitle() {
    binding.toolbarInclude.toolbar.title = getString(R.string.coins_list_title)
  }
}
//
//  override fun selectCoin(coinBase: CoinBase) {
//    val action = ListFragmentDirections.actionListFragmentToCoinDataFragment(coinBase)
//    navController.navigate(action)
//  }