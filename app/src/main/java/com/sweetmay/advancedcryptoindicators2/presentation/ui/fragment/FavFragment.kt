package com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment

import android.os.Bundle
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
import com.sweetmay.advancedcryptoindicators2.databinding.FavFragmentBinding
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment.base.BaseFragment
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.FavViewModel
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.FavViewState
import kotlinx.coroutines.flow.collect

class FavFragment : BaseFragment<FavFragmentBinding, List<CoinView>>() {

  override val viewModel: FavViewModel by navGraphViewModels(R.id.nav_graph)

  private lateinit var navController: NavController

  override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FavFragmentBinding {
    navController = findNavController()
    return FavFragmentBinding.inflate(inflater, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    inflateToolbar(binding.toolbarInclude.toolbar, R.menu.fav_menu)
    setTitle()
    initRv()
    viewModel.loadData()
    lifecycleScope.launchWhenCreated {
      viewModel.uiState.collect {
        when (it) {
          is FavViewState.Loading -> {
            binding.toolbarInclude.progressBar.show()
          }
          is FavViewState.Success -> {
            binding.toolbarInclude.progressBar.hide()
            viewModel.adapter.coins.addAll(it.value)
            viewModel.adapter.notifyDataSetChanged()
          }
          is FavViewState.Error -> {
            Toast.makeText(requireContext(), it.errorMsg, Toast.LENGTH_SHORT).show()
          }
          is FavViewState.RemovingElement -> {
            with(viewModel.adapter) {
              notifyItemRemoved(it.pos)
              notifyItemRangeChanged(it.pos, viewModel.adapter.coins.size)
            }
          }
          is FavViewState.Empty -> showNoCoins()
        }
      }
    }
  }

  private fun initRv() {
    with(binding.coinFavRv) {
      layoutManager = LinearLayoutManager(context)
      adapter = viewModel.adapter
    }
  }

  private fun setTitle() {
    with(binding.toolbarInclude.toolbar) {
      title = getString(R.string.favorite_coins_title)
      logo = null
    }
  }

//    override fun navigateToDetailed(coinBase: CoinBase) {
//        val action = FavFragmentDirections.actionFavFragmentToCoinDataFragment(coinBase)
//        navController.navigate(action)
//    }

  private fun showNoCoins() {
    binding.toolbarInclude.progressBar.hide()
    binding.error.text = getString(R.string.nothing_to_show)
  }

}