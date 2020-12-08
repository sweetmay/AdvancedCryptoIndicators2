package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.FavFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.cache.room.FavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presenter.FavListPresenter
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoader
import com.sweetmay.advancedcryptoindicators2.view.FavView
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.ktx.moxyPresenter

class FavFragment : BaseFragment<FavFragmentBinding>(), FavView {

    lateinit var navController: NavController

    val presenter: FavListPresenter by moxyPresenter {
        FavListPresenter(CoinsListRepo(ApiHolder(App.BASE_URL),
                FavCoinsCache(App.instance.dao)),
                AndroidSchedulers.mainThread(),
                FavCoinsCache(App.instance.dao))
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FavFragmentBinding {
        navController = findNavController()
        return FavFragmentBinding.inflate(inflater, container, false)
    }

    override fun initRv() {
        with(binding.coinFavRv) {
            layoutManager = LinearLayoutManager(context)
            adapter = CoinsListAdapter(presenter.listPresenter, GlideImageLoader())
        }
    }

    override fun updateList() {
        binding.coinFavRv.adapter?.notifyDataSetChanged()
    }

    override fun notifyItemRemoved(pos: Int, newSize: Int) {
        with(binding.coinFavRv.adapter) {
            this?.notifyItemRemoved(pos)
            this?.notifyItemRangeChanged(pos, newSize)
        }
    }

    override fun setTitle() {
        with(binding.toolbarInclude.toolbar) {
            title = getString(R.string.favorite_coins_title)
            logo = null
        }
    }

    override fun navigateToDetailed(coinBase: CoinBase) {
        val action = FavFragmentDirections.actionFavFragmentToCoinDataFragment(coinBase)
        navController.navigate(action)
    }

    override fun showNoCoins() {
        binding.error.text = getString(R.string.nothing_to_show)
    }
}