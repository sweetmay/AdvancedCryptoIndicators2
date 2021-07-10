//package com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.navigation.NavController
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.sweetmay.advancedcryptoindicators2.App
//import com.sweetmay.advancedcryptoindicators2.R
//import com.sweetmay.advancedcryptoindicators2.databinding.FavFragmentBinding
//import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
//import com.sweetmay.advancedcryptoindicators2.presenter.FavListFragmentPresenter
//import com.sweetmay.advancedcryptoindicators2.view.FavView
//import com.sweetmay.advancedcryptoindicators2.presentation.ui.fragment.base.BaseFragment
//import moxy.ktx.moxyPresenter
//
//class FavFragment : BaseFragment<FavFragmentBinding>(), FavView {
//
//    private lateinit var navController: NavController
//
//    private val presenter: FavListFragmentPresenter by moxyPresenter {
//        FavListFragmentPresenter(App.injection)
//    }
//
//    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FavFragmentBinding {
//        navController = findNavController()
//        return FavFragmentBinding.inflate(inflater, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        inflateToolbar(binding.toolbarInclude.toolbar, R.menu.fav_menu)
//    }
//
//    override fun initRv() {
//        with(binding.coinFavRv) {
//            layoutManager = LinearLayoutManager(context)
//            adapter = presenter.createAdapter()
//        }
//    }
//
//    override fun updateList() {
//        binding.coinFavRv.adapter?.notifyDataSetChanged()
//    }
//
//    override fun notifyItemRemoved(pos: Int, newSize: Int) {
//        with(binding.coinFavRv.adapter) {
//            this?.notifyItemRemoved(pos)
//            this?.notifyItemRangeChanged(pos, newSize)
//        }
//    }
//
//    override fun setTitle() {
//        with(binding.toolbarInclude.toolbar) {
//            title = getString(R.string.favorite_coins_title)
//            logo = null
//        }
//    }
//
//    override fun showLoading() {
//        binding.toolbarInclude.progressBar.show()
//    }
//
//    override fun hideLoading() {
//        binding.toolbarInclude.progressBar.hide()
//    }
//
//    override fun navigateToDetailed(coinBase: CoinBase) {
//        val action = FavFragmentDirections.actionFavFragmentToCoinDataFragment(coinBase)
//        navController.navigate(action)
//    }
//
//    override fun showNoCoins() {
//        binding.error.text = getString(R.string.nothing_to_show)
//    }
//
//    override fun onErrorHandleClick() {
//        presenter.loadData()
//    }
//
//}