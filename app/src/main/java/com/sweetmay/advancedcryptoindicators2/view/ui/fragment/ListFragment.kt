package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presenter.ListFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import com.sweetmay.advancedcryptoindicators2.view.CoinsListView
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import com.sweetmay.advancedcryptoindicators2.view.image.GlideImageLoader
import com.sweetmay.advancedcryptoindicators2.view.image.GlideImageLoaderAsDrawable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ListFragment: MvpAppCompatFragment(R.layout.list_fragment), CoinsListView {

    val presenter: ListFragmentPresenter by moxyPresenter { ListFragmentPresenter(CoinsListRepo(
            ApiHolder(App.BASE_URL)
    ), AndroidSchedulers.mainThread()) }

    lateinit var toolbar: Toolbar
    lateinit var recyclerView: RecyclerView
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.coin_rv)
        navController = findNavController()
    }

    override fun initRv() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CoinsListAdapter(presenter.coinsListPresenter, GlideImageLoader())
    }

    override fun setTitle() {
        toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.app_name)
        toolbar.logo = null
    }

    override fun updateList() {
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun selectCoin(coinBase: CoinBase) {
        val action = ListFragmentDirections.actionListFragmentToCoinDataFragment(coinBase)
        navController.navigate(action)
    }

}