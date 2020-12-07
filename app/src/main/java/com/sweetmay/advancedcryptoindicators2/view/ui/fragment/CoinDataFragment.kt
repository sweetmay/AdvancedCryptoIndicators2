package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.CoinDataFragmentBinding
import com.sweetmay.advancedcryptoindicators2.databinding.ListFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinDataRepo
import com.sweetmay.advancedcryptoindicators2.presenter.CoinDataFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import com.sweetmay.advancedcryptoindicators2.view.CoinDataView
import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoaderAsDrawable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CoinDataFragment: MvpAppCompatFragment(), CoinDataView {

    private var _binding: CoinDataFragmentBinding? = null
    private val binding get() = _binding!!

    val presenter: CoinDataFragmentPresenter by moxyPresenter {
        CoinDataFragmentPresenter(CoinDataRepo(ApiHolder(App.BASE_URL)),
                AndroidSchedulers.mainThread(),
                GlideImageLoaderAsDrawable(requireContext())) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CoinDataFragmentBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarInclude.toolbar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coin = arguments?.let { CoinDataFragmentArgs.fromBundle(it).coin }
        if (coin != null) {
            presenter.loadCoinData(coin)
        }
    }

    override fun setPrice(price: String) {
        binding.priceDetailed.text = price
    }

    override fun set24hChange(change: String) {
        binding.priceChange.text = change
    }

    override fun showLoading() {
        binding.progressBar.show()
    }

    override fun hideLoading() {
        binding.progressBar.hide()
    }

    override fun setTitle(title: String) {
        binding.toolbarInclude.toolbar.title = title
    }

    override fun setLogo(image: Drawable) {
        binding.toolbarInclude.toolbarLogo.setImageDrawable(image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}