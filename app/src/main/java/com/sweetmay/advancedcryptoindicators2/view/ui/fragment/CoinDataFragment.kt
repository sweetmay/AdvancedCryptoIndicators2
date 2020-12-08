package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.animation.ArgbEvaluator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Color.red
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.CoinDataFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinDataRepo
import com.sweetmay.advancedcryptoindicators2.utils.rsi.RsiEvaluator
import com.sweetmay.advancedcryptoindicators2.presenter.CoinDataFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import com.sweetmay.advancedcryptoindicators2.utils.converter.PriceConverter
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
                GlideImageLoaderAsDrawable(requireContext()), RsiEvaluator(ArgbEvaluator())) }

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

    override fun set24hChange(convertedChange: PriceConverter.ConvertedChange) {
        binding.priceChange.text = convertedChange.convertedPriceString
        binding.priceChange.setTextColor(convertedChange.color)
    }

    override fun showLoading() {
        binding.toolbarInclude.progressBar.show()
    }

    override fun hideLoading() {
        binding.toolbarInclude.progressBar.hide()
    }

    override fun setTitle(title: String) {
        binding.toolbarInclude.toolbar.title = title
    }

    override fun setLogo(image: Drawable) {
        binding.toolbarInclude.toolbarLogo.setImageDrawable(image)
    }

    override fun setRsi(rsi: RsiEvaluator.RsiEntity) {
        with(binding){
            possibleEntry.text = resources.getString(R.string.possible_entry, rsi.possibleEntry)
            stopLoss.text = resources.getString(R.string.possible_sl, rsi.stopLoss)
            rsiStrength.text = resources.getString(R.string.rsi_strength, String.format("%.2f", rsi.rsi))
            rsiStrength.setTextColor(rsi.getRsiColor())
            target.text = resources.getString(R.string.possible_target, rsi.possibleTarget)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}