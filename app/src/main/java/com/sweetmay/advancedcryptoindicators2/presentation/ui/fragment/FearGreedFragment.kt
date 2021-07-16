package com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.doOnLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.FearGreedFragmentBinding
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.fng.FnGView
import com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment.base.BaseFragment
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.FearGreedViewModel
import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.viewstate.FnGViewState
import kotlinx.coroutines.flow.collect

class FearGreedFragment : BaseFragment<FearGreedFragmentBinding, FnGView>() {

  override val viewModel: FearGreedViewModel by navGraphViewModels(R.id.nav_graph)

  private lateinit var navController: NavController

  override fun setBinding(
    inflater: LayoutInflater,
    container: ViewGroup?
  ): FearGreedFragmentBinding {
    navController = findNavController()
    return FearGreedFragmentBinding.inflate(inflater, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    inflateToolbar(binding.toolbarInclude.toolbar, R.menu.fng_menu)
    setTitle()

    lifecycleScope.launchWhenCreated {
      viewModel.uiState.collect {
        when (it) {
          is FnGViewState.Loading -> binding.toolbarInclude.progressBar.show()
          is FnGViewState.Success -> renderFng(it.value)
          is FnGViewState.Error -> renderError(it.errorMsg)
          else -> {

          }
        }
      }
    }

  }

  private fun renderError(errorMsg: String) {
    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
  }

  private fun renderFng(data: FnGView) {
    animateFnGView(data.currentFng.first)
    binding.toolbarInclude.progressBar.hide()
    with(binding) {
      textNowFngStatus.text = data.currentFng.second
      valueNow.text = data.currentFng.first.toString()
      textYesterdayFngStatus.text = data.yesterdayFng.second
      valueYesterday.text = data.yesterdayFng.first.toString()
      textLastWeekFngStatus.text = data.lastWeekFng.second
      valueLastWeek.text = data.lastWeekFng.first.toString()
      valueLastMonth.text = data.lastMonthFng.first.toString()
      textLastMonthFngStatus.text = data.lastMonthFng.second
    }
  }

  override fun inflateToolbar(toolbar: Toolbar, menuRes: Int) {
    super.inflateToolbar(toolbar, menuRes)
    with(toolbar) {
      setNavigationIcon(R.drawable.navigate_before_24px)
      setNavigationOnClickListener {
        requireActivity().onBackPressed()
      }
//            setOnMenuItemClickListener {
//                when(it.itemId){
//                    R.id.help_button_menu->navController.navigate(FearGreedFragmentDirections
//                            .actionFearGreedFragmentToHelpFngFragment())
//                }
//                true
//            }
    }
  }

  private fun setTitle() {
    binding.toolbarInclude.toolbar.title = getString(R.string.fear_greed_title)
  }

  private fun animateFnGView(fng: Int) {
    with(binding.fearGreedView) {
      doOnLayout {
        this.rotateToAnimation(fng)
      }
    }
  }

}