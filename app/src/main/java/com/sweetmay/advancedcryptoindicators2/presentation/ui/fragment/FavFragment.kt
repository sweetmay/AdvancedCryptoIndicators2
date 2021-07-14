//package com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.navigation.NavController
//import androidx.navigation.fragment.findNavController
//import androidx.navigation.navGraphViewModels
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.sweetmay.advancedcryptoindicators2.R
//import com.sweetmay.advancedcryptoindicators2.databinding.FavFragmentBinding
//import com.sweetmay.advancedcryptoindicators2.model.entity.crypto.base_coin.CoinView
//import com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment.base.BaseFragment
//import com.sweetmay.advancedcryptoindicators2.presentation.viewmodel.base.BaseViewModel
//
//class FavFragment : BaseFragment<FavFragmentBinding, CoinView>() {
//
//  override val viewModel: BaseViewModel<CoinView> by navGraphViewModels(R.id.nav_graph)
//
//  private lateinit var navController: NavController
//
//  override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FavFragmentBinding {
//    navController = findNavController()
//    return FavFragmentBinding.inflate(inflater, container, false)
//  }
//
//  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//    inflateToolbar(binding.toolbarInclude.toolbar, R.menu.fav_menu)
//    setTitle()
//  }
//
//  private fun initRv() {
//    with(binding.coinFavRv) {
//      layoutManager = LinearLayoutManager(context)
//      adapter = viewModel.createAdapter()
//    }
//  }
//
//  override fun updateList() {
//    binding.coinFavRv.adapter?.notifyDataSetChanged()
//  }
//
//  override fun notifyItemRemoved(pos: Int, newSize: Int) {
//    with(binding.coinFavRv.adapter) {
//      this?.notifyItemRemoved(pos)
//      this?.notifyItemRangeChanged(pos, newSize)
//    }
//  }
//
//  private fun setTitle() {
//    with(binding.toolbarInclude.toolbar) {
//      title = getString(R.string.favorite_coins_title)
//      logo = null
//    }
//  }
//
////    override fun navigateToDetailed(coinBase: CoinBase) {
////        val action = FavFragmentDirections.actionFavFragmentToCoinDataFragment(coinBase)
////        navController.navigate(action)
////    }
//
//  private fun showNoCoins() {
//    binding.error.text = getString(R.string.nothing_to_show)
//  }
//
//}