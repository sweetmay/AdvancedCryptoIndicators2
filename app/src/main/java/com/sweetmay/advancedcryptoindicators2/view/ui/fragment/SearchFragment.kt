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
import com.sweetmay.advancedcryptoindicators2.utils.exception.NoResultsException
import com.sweetmay.advancedcryptoindicators2.view.SearchView
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment
import moxy.ktx.moxyPresenter

class SearchFragment: BaseFragment<SearchFragmentBinding>(), SearchView {

    private val presenter: SearchFragmentPresenter by moxyPresenter { SearchFragmentPresenter(App.injection) }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.fetchAllCoins()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarInclude.toolbar.setNavigationIcon(R.drawable.navigate_before_24px)
        binding.toolbarInclude.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        setOnSearchQueryListener()


    }

    private fun setOnSearchQueryListener() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.length > 1 && newText != "") {
                    presenter.searchForCoins(newText)
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
            adapter = presenter.createAdapter()
        }
    }

    override fun onErrorHandleClick() {
        presenter.fetchAllCoins()
    }

    override fun updateList() {
        binding.noResultText.visibility = View.GONE
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

    override fun renderError(e: Exception) {
        if(e is NoResultsException){
            binding.noResultText.visibility = View.VISIBLE
        }else{
            super.renderError(e)
        }
    }
}