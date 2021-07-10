package com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.ListFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment.base.BaseFragment
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.MainListViewModel
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.ViewState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

class ListFragment : BaseFragment<ListFragmentBinding, List<CoinBase>>() {

  override val viewModel: MainListViewModel by viewModels()

  private lateinit var navController: NavController

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    inflateToolbar(binding.toolbarInclude.toolbar, R.menu.options_menu_main)
    navigateToSearch()
    initRv()
    lifecycleScope.launchWhenCreated {
      viewModel.uiState.collect {
        when(it){
          is ViewState.Loading -> binding.toolbarInclude.progressBar.show()
          is ViewState.Success -> {
            binding.toolbarInclude.progressBar.hide()
            viewModel.addCoinsToAdapter(it.value)
            binding.coinRv.adapter?.notifyDataSetChanged()
          }
          is ViewState.Error -> {
            Toast.makeText(requireContext(), "error", Toast.LENGTH_LONG).show()
          }
          is ViewState.Empty -> {
            Toast.makeText(requireContext(), "empty", Toast.LENGTH_LONG).show()
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
      adapter = viewModel.createAdapter()
//      extendListOnScroll()
    }
  }

  override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): ListFragmentBinding? {
    navController = findNavController()
    return ListFragmentBinding.inflate(inflater, container, false)
  }
}

//  private fun RecyclerView.extendListOnScroll() {
//    addOnScrollListener(object : RecyclerView.OnScrollListener() {
//      override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//        super.onScrollStateChanged(recyclerView, newState)
//        if (!recyclerView.canScrollVertically(1)
//          && newState == RecyclerView.SCROLL_STATE_IDLE
//        ) {
//          viewModel.saveRVPos(
//            (layoutManager as LinearLayoutManager)
//              .findFirstCompletelyVisibleItemPosition()
//          )
//          viewModel.loadData()
//        }
//      }
//    })
//  }

//  override fun onPause() {
//    super.onPause()
//    viewModel.saveRVPos(
//      (binding.coinRv.layoutManager as LinearLayoutManager)
//        .findFirstCompletelyVisibleItemPosition()
//    )
//}
//
//  override fun setTitle() {
//    binding.toolbarInclude.toolbar.title = getString(R.string.coins_list_title)
//  }
//
//  override fun restoreRVPosition(pos: Int) {
//    binding.coinRv.layoutManager?.scrollToPosition(presenter.restoreRVState())
//  }
//
//  override fun showLoading() {
//    binding.toolbarInclude.progressBar.show()
//  }
//
//  override fun hideLoading() {
//    binding.toolbarInclude.progressBar.hide()
//  }
//
//  override fun selectCoin(coinBase: CoinBase) {
//    val action = ListFragmentDirections.actionListFragmentToCoinDataFragment(coinBase)
//    navController.navigate(action)
//  }