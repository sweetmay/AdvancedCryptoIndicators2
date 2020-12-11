package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.ListFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.presenter.ListFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoader
import com.sweetmay.advancedcryptoindicators2.view.CoinsListView
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment
import moxy.ktx.moxyPresenter

class ListFragment : BaseFragment<ListFragmentBinding>(), CoinsListView {

    val presenter: ListFragmentPresenter by moxyPresenter {
        ListFragmentPresenter()
    }

    lateinit var navController: NavController

    override fun initRv() {
        with(binding.coinRv){
            layoutManager = LinearLayoutManager(context)
            adapter = CoinsListAdapter(presenter.coinsListPresenter, GlideImageLoader())
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.saveRVPos((binding.coinRv.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition())
    }

    override fun updateList() {
        binding.coinRv.adapter?.notifyDataSetChanged()
    }

    override fun setTitle() {
        binding.toolbarInclude.toolbar.title = getString(R.string.coins_lis_title)
    }

    override fun restoreRVposition(pos: Int) {
        binding.coinRv.layoutManager?.scrollToPosition(presenter.restoreRVState())
    }

    override fun showLoading() {
        binding.toolbarInclude.progressBar.show()
    }

    override fun hideLoading() {
        binding.toolbarInclude.progressBar.hide()
    }

    override fun selectCoin(coinBase: CoinBase) {
        val action = ListFragmentDirections.actionListFragmentToCoinDataFragment(coinBase)
        navController.navigate(action)
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): ListFragmentBinding {
        navController = findNavController()
        return ListFragmentBinding.inflate(inflater, container, false)
    }


}