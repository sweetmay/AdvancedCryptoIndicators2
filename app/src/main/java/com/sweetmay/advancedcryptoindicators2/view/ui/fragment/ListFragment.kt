package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.ListFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.presenter.ListFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.view.CoinsListView
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment
import moxy.ktx.moxyPresenter

class ListFragment : BaseFragment<ListFragmentBinding>(), CoinsListView {

    val presenter: ListFragmentPresenter by moxyPresenter { ListFragmentPresenter(App.injection) }

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToSearch()
    }

    private fun navigateToSearch() {
        with(binding.toolbarInclude.toolbar) {
            inflateMenu(R.menu.options_menu_search)
            menu.findItem(R.id.search_button_menu).setOnMenuItemClickListener {
                navController.navigate(ListFragmentDirections.actionListFragmentToSearchFragment())
                true
            }
        }
    }


    override fun initRv() {
        with(binding.coinRv){
            layoutManager = LinearLayoutManager(context)
            adapter = presenter.createAdapter()
            extendListOnScroll()
        }
    }

    private fun RecyclerView.extendListOnScroll() {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    presenter.saveRVPos(
                        (layoutManager as LinearLayoutManager)
                            .findFirstCompletelyVisibleItemPosition()
                    )
                    presenter.loadData()
                }
            }
        })
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
        binding.toolbarInclude.toolbar.title = getString(R.string.coins_list_title)
    }

    override fun restoreRVPosition(pos: Int) {
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

    override fun onErrorHandleClick() {
        presenter.loadData()
    }


}