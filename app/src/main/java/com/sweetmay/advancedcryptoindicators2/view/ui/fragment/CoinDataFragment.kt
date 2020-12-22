package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.CoinDataFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.presenter.CoinDataFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.rsi.RsiEntity
import com.sweetmay.advancedcryptoindicators2.view.CoinDataView
import com.sweetmay.advancedcryptoindicators2.view.custom.FavButton
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment
import moxy.ktx.moxyPresenter

class CoinDataFragment : BaseFragment<CoinDataFragmentBinding>(), CoinDataView {

    private val presenter: CoinDataFragmentPresenter by moxyPresenter {
        CoinDataFragmentPresenter(App.injection)
    }
    private var pendingCoin: CoinBase? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pendingCoin = arguments?.let { CoinDataFragmentArgs.fromBundle(it).coin }
        pendingCoin?.let { presenter.loadData(it) }

        binding.currentPeriod.text = getString(R.string.current_prediction_period, 30)
        binding.dayPredictionPicker.setOnClickListener {
            showPopUpMenu(it)
        }
    }

    private fun showPopUpMenu(view: View) {
        val menu = PopupMenu(context, view)
        menu.inflate(R.menu.arima_settings_menu)
        menu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id._30_day -> {
                    presenter.changeArima(30, pendingCoin)
                    binding.currentPeriod.text = getString(R.string.current_prediction_period, 30)
                    return@setOnMenuItemClickListener true
                }
                R.id._60_day -> {
                    presenter.changeArima(60, pendingCoin)
                    binding.currentPeriod.text = getString(R.string.current_prediction_period, 60)
                    return@setOnMenuItemClickListener true
                }
                R.id._90_day -> {
                    presenter.changeArima(90, pendingCoin)
                    binding.currentPeriod.text = getString(R.string.current_prediction_period, 90)
                    return@setOnMenuItemClickListener true
                }
                R.id.year_prediction -> {
                    presenter.changeArima(365, pendingCoin)
                    binding.currentPeriod.text = getString(R.string.current_prediction_period, 365)
                    return@setOnMenuItemClickListener true
                }else-> return@setOnMenuItemClickListener false
            }
        }
        menu.show()
    }

    override fun setPrice(price: String) {
        binding.priceDetailed.text = price
    }

    override fun set24hChange(convertedChange: Converter.ConvertedChange) {
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

    override fun setFavButton(coinBase: CoinBase) {
        with(binding.favButton){
            checked = coinBase.is_favorite
            this.setOnClickListener {
                (it as FavButton).toggle()
                if(it.checked){
                    presenter.saveToCache(coinBase)
                }else presenter.deleteFromCache(coinBase)
            }
        }


    }

    override fun setSentimentView(value: Int) {
        binding.sentiment.setSentiment(value)
    }

    override fun setRsi(rsi: RsiEntity) {
        with(binding) {
            stopLoss.text = resources.getString(R.string.possible_sl, String.format("%.6f",rsi.stopLoss), String.format("%.2f", rsi.possibleSLPerc))
            rsiStrength.text = resources.getString(R.string.rsi_strength, String.format("%.2f", rsi.signalStrength))
            rsiStrength.setTextColor(rsi.indicatorColor)
            target.text = resources.getString(R.string.possible_target, String.format("%.6f",rsi.possibleTarget), String.format("%.2f", rsi.possibleTargetPerc))
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
        with(binding){
            rsiStrength.text = getString(R.string.not_enough_data_error)
            stopLoss.text = getString(R.string.not_enough_data_error)
            target.text = getString(R.string.not_enough_data_error)
        }
    }



    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): CoinDataFragmentBinding {
        return CoinDataFragmentBinding.inflate(inflater, container, false)
    }

    override fun onErrorHandleClick() {
        pendingCoin?.let { presenter.loadData(it) }
    }

}