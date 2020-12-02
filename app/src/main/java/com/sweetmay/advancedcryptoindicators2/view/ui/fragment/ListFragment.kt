package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presenter.ListFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import com.sweetmay.advancedcryptoindicators2.view.CoinsListView
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import com.sweetmay.advancedcryptoindicators2.view.image.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ListFragment: MvpAppCompatFragment(R.layout.list_fragment), CoinsListView {

    val presenter: ListFragmentPresenter by moxyPresenter { ListFragmentPresenter(CoinsListRepo(
        ApiHolder(App.BASE_URL)
    ), AndroidSchedulers.mainThread()) }
    lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.coin_rv)
    }

    override fun initRv() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CoinsListAdapter(presenter.coinsListPresenter, GlideImageLoader())
    }

    override fun updateList() {
        recyclerView.adapter?.notifyDataSetChanged()
    }

}