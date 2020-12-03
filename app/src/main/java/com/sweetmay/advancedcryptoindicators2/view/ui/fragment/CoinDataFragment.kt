package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.detailed.CoinDetailed
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinDataRepo
import com.sweetmay.advancedcryptoindicators2.presenter.CoinDataFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import com.sweetmay.advancedcryptoindicators2.view.CoinDataView
import com.sweetmay.advancedcryptoindicators2.view.image.GlideImageLoader
import com.sweetmay.advancedcryptoindicators2.view.image.GlideImageLoaderAsDrawable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import org.w3c.dom.Text

class CoinDataFragment: MvpAppCompatFragment(R.layout.coin_data_fragment), CoinDataView {

    val presenter: CoinDataFragmentPresenter by moxyPresenter {
        CoinDataFragmentPresenter(CoinDataRepo(ApiHolder(App.BASE_URL)),
                AndroidSchedulers.mainThread(),
                GlideImageLoaderAsDrawable(requireContext())) }

    lateinit var toolbar: Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coin = arguments?.let { CoinDataFragmentArgs.fromBundle(it).coin }

        if (coin != null) {
            presenter.loadCoinData(coin)
        }

    }

    override fun setPrice(price: String) {
        requireActivity().findViewById<TextView>(R.id.price_detailed).text = price
    }

    override fun set24hChange(change: String) {
        requireActivity().findViewById<TextView>(R.id.price_change).text = change
    }

    override fun setTitle(string: String) {
        activity?.let {
            toolbar = it.findViewById(R.id.toolbar)
            toolbar.title = string
        }
    }

    override fun setLogo(image: Drawable) {
        toolbar.logo = image
    }

}