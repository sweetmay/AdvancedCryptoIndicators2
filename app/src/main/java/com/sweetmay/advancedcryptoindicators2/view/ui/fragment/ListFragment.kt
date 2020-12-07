package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.ListFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.cache.room.FavCoinsCache
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presenter.ListFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoader
import com.sweetmay.advancedcryptoindicators2.view.CoinsListView
import com.sweetmay.advancedcryptoindicators2.view.adapter.CoinsListAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ListFragment: MvpAppCompatFragment(), CoinsListView {

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    val presenter: ListFragmentPresenter by moxyPresenter { ListFragmentPresenter(CoinsListRepo(ApiHolder(App.BASE_URL),
            FavCoinsCache(App.instance.dao)), AndroidSchedulers.mainThread(), FavCoinsCache(App.instance.dao)) }

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        navController = findNavController()
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarInclude.toolbar)
        return binding.root
    }

    override fun initRv() {
        binding.coinRv.layoutManager = LinearLayoutManager(context)
        binding.coinRv.adapter = CoinsListAdapter(presenter.coinsListPresenter, GlideImageLoader())
    }

    override fun updateList() {
        binding.coinRv.adapter?.notifyDataSetChanged()
    }

    override fun setTitle() {
        binding.toolbarInclude.toolbar.title = getString(R.string.coins_lis_title)
    }

    override fun selectCoin(coinBase: CoinBase) {
        val action = ListFragmentDirections.actionListFragmentToCoinDataFragment(coinBase)
        navController.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}