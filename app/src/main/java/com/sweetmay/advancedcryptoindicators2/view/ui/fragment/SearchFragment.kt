package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.SearchFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.presenter.SearchFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoader
import com.sweetmay.advancedcryptoindicators2.view.SearchView
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment
import moxy.ktx.moxyPresenter

class SearchFragment: BaseFragment<SearchFragmentBinding>(), SearchView {

    val fragmentPresenter: SearchFragmentPresenter by moxyPresenter { SearchFragmentPresenter(App.injection) }

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentPresenter.fetchAllcoins()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.length>1 && newText != "") {
                    fragmentPresenter.searchForCoins(newText)
                }
                return true
            }

        })
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): SearchFragmentBinding {
        navController = findNavController()
        return SearchFragmentBinding.inflate(inflater, container, false)
    }

    override fun initRV() {
        with(binding.coinRv){
            layoutManager = LinearLayoutManager(context)
            adapter = CoinsListAdapter(fragmentPresenter.coinsListPresenter, GlideImageLoader())
        }
    }

    override fun onErrorHandleClick() {
        fragmentPresenter.fetchAllcoins()
    }

    override fun updateList() {
        binding.coinRv.adapter?.notifyDataSetChanged()
    }

    override fun setTitle() {
        binding.toolbarInclude.toolbar.title = getString(R.string.search_fragment_title)
    }

    override fun showLoading() {
        binding.toolbarInclude.progressBar.show()
    }

    override fun hideLoading() {
        binding.toolbarInclude.progressBar.hide()
    }

    override fun selectCoin(coinBase: CoinBase) {
        val action = SearchFragmentDirections.actionSearchFragmentToCoinDataFragment(coinBase)
        navController.navigate(action)
    }
}