package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.animation.ArgbEvaluator
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.CoinDataFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinDataRepo
import com.sweetmay.advancedcryptoindicators2.presenter.CoinDataFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.utils.ApiHolder
import com.sweetmay.advancedcryptoindicators2.utils.converter.PriceConverter
import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoaderAsDrawable
import com.sweetmay.advancedcryptoindicators2.utils.rsi.RsiEntity
import com.sweetmay.advancedcryptoindicators2.utils.rsi.RsiEvaluator
import com.sweetmay.advancedcryptoindicators2.view.CoinDataView
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.ktx.moxyPresenter

class CoinDataFragment : BaseFragment<CoinDataFragmentBinding>(), CoinDataView {

    val presenter: CoinDataFragmentPresenter by moxyPresenter {
        CoinDataFragmentPresenter(CoinDataRepo(ApiHolder(App.BASE_URL)),
                AndroidSchedulers.mainThread(),
                GlideImageLoaderAsDrawable(requireContext()), RsiEvaluator(ArgbEvaluator()))
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

    override fun setRsi(rsi: RsiEntity) {
        with(binding) {
            stopLoss.text = resources.getString(R.string.possible_sl, String.format("%.3f",rsi.stopLoss))
            rsiStrength.text = resources.getString(R.string.rsi_strength, String.format("%.2f", rsi.signalStrength))
            rsiStrength.setTextColor(rsi.indicatorColor)
            target.text = resources.getString(R.string.possible_target, String.format("%.3f",rsi.possibleTarget))
            if(rsi.isPositive){
                buySellText.text = getString(R.string.buy_text)
            }else {
                buySellText.text = getString(R.string.sell_text)
            }
            buySellText.setTextColor(rsi.indicatorColor)
        }

    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): CoinDataFragmentBinding? {
        return CoinDataFragmentBinding.inflate(inflater, container, false)
    }

}