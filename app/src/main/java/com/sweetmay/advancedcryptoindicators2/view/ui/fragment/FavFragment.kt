package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.model.cache.room.FavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presenter.FavListPresenter
import com.sweetmay.advancedcryptoindicators2.presenter.list.CoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import com.sweetmay.advancedcryptoindicators2.view.FavView
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import com.sweetmay.advancedcryptoindicators2.view.image.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FavFragment: MvpAppCompatFragment(R.layout.fav_fragment), FavView {

    val presenter: FavListPresenter by moxyPresenter { FavListPresenter(CoinsListRepo(ApiHolder(App.BASE_URL),
            FavCoinsCache(App.instance.dao)),
            AndroidSchedulers.mainThread(),
            FavCoinsCache(App.instance.dao)) }

    lateinit var recyclerView: RecyclerView
    lateinit var navController: NavController
    lateinit var errorText: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.coin_fav_rv)
        errorText = view.findViewById(R.id.error)
        navController = findNavController()
    }

    override fun initRv() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CoinsListAdapter(presenter.listPresenter, GlideImageLoader())
    }

    override fun updateList() {
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun notifyItemRemoved(pos: Int, newSize: Int) {
        with(recyclerView.adapter){
            this?.notifyItemRemoved(pos)
            this?.notifyItemRangeChanged(pos, newSize)
        }
    }

    override fun navigateToDetailed(coinBase: CoinBase) {
        val action = FavFragmentDirections.actionFavFragmentToCoinDataFragment(coinBase)
        navController.navigate(action)
    }

    override fun showNoCoins() {
        errorText.text = getString(R.string.nothing_to_show)
    }
}