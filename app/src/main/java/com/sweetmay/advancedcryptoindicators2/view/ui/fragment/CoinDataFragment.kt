package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.CoinDataFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.presenter.CoinDataFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.utils.converter.PriceConverter
import com.sweetmay.advancedcryptoindicators2.utils.rsi.RsiEntity
import com.sweetmay.advancedcryptoindicators2.view.CoinDataView
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment
import moxy.ktx.moxyPresenter

class CoinDataFragment : BaseFragment<CoinDataFragmentBinding>(), CoinDataView {

    private val presenter: CoinDataFragmentPresenter by moxyPresenter { CoinDataFragmentPresenter() }
    private var pendingCoin: CoinBase? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pendingCoin = arguments?.let { CoinDataFragmentArgs.fromBundle(it).coin }
        pendingCoin?.let { presenter.loadData(it) }
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

    override fun setArima(prediction: String) {
        binding.prediction.text = resources.getString(R.string.prediction_text, prediction)
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

    override fun showArimaError() {
        binding.prediction.text = getString(R.string.not_enough_data_error)
    }

    override fun showRsiError() {

    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): CoinDataFragmentBinding {
        return CoinDataFragmentBinding.inflate(inflater, container, false)
    }

    override fun onErrorHandleClick() {
        pendingCoin?.let { presenter.loadData(it) }
    }

}