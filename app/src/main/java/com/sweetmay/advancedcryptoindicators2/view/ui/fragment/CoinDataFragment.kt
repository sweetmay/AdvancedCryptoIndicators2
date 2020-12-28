package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.CoinDataFragmentBinding
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.settings.ISettings
import com.sweetmay.advancedcryptoindicators2.presenter.CoinDataFragmentPresenter
import com.sweetmay.advancedcryptoindicators2.utils.arima.ArimaEntity
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.rsi.RsiEntity
import com.sweetmay.advancedcryptoindicators2.view.CoinDataView
import com.sweetmay.advancedcryptoindicators2.view.custom.FavButton
import com.sweetmay.advancedcryptoindicators2.view.ui.dialogs.ArimaCallBack
import com.sweetmay.advancedcryptoindicators2.view.ui.dialogs.ArimaSettingsDialog
import com.sweetmay.advancedcryptoindicators2.view.ui.dialogs.RsiCallBack
import com.sweetmay.advancedcryptoindicators2.view.ui.dialogs.RsiSettingsDialog
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.base.BaseFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CoinDataFragment : BaseFragment<CoinDataFragmentBinding>(), CoinDataView, ArimaCallBack, RsiCallBack {

    init {
        App.injection.initDetailedComponent()?.inject(this)
    }

    @Inject
    lateinit var converter: Converter
    @Inject
    lateinit var settings: ISettings

    lateinit var navController: NavController

    private val presenter: CoinDataFragmentPresenter by moxyPresenter {
        CoinDataFragmentPresenter(App.injection)
    }
    private var pendingCoin: CoinBase? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pendingCoin = arguments?.let { CoinDataFragmentArgs.fromBundle(it).coin }
        pendingCoin?.let { presenter.loadData(it) }

        initArimaIndicator()
        initRsiIndicator()
    }

    private fun initRsiIndicator() {
        with(binding){
            timeFrameRsi.text = getString(R.string.time_frame,
                    getString(settings.getRsiTimeFrameRes()))
            riskRewardRsi.text = getString(R.string.risk_reward,
                    getString(settings.getRsiRiskRewardRes()))
            settingsRsi.setOnClickListener {
                val dialog = RsiSettingsDialog
                        .getInstance(this@CoinDataFragment, settings)
                dialog.show(requireActivity().supportFragmentManager, "RsiSettingsDialog")
            }
        }
    }

    private fun initArimaIndicator() {
        with(binding){
            timeFrameArima.text = getString(R.string.time_frame,
                    getString(settings.getArimaTimeFrameRes()))
            currentPeriod.text = getString(R.string.current_prediction_period,
                    settings.arimaPredictionPeriod)
            settingsArima.setOnClickListener {
                val dialog = ArimaSettingsDialog
                        .getInstance(this@CoinDataFragment, settings)
                dialog.show(requireActivity().supportFragmentManager, "ArimaSettingsDialog")
            }
        }

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

    override fun setArima(arimaEntity: ArimaEntity) {
        with(binding){
            prediction.text = resources.getString(R.string.prediction_text,
                    converter.convertPriceArima(arimaEntity.forecastLast))
            if(arimaEntity.isPositive){
                buySellTextArima.text = getString(R.string.buy_text)
            }else {
                buySellTextArima.text = getString(R.string.sell_text)
            }
        }
    }

    override fun saveArimaSettings() {
        with(binding){
            currentPeriod.text = getString(R.string.current_prediction_period,
                    settings.arimaPredictionPeriod)
            timeFrameArima.text = getString(R.string.time_frame,
                    getString(settings.getArimaTimeFrameRes()))

            presenter.loadArima(pendingCoin)
        }
    }

    override fun saveRsiSettings() {
        with(binding){
            timeFrameRsi.text = getString(R.string.time_frame,
                    getString(settings.getRsiTimeFrameRes()))
            riskRewardRsi.text = getString(R.string.risk_reward,
                    getString(settings.getRsiRiskRewardRes()))

            presenter.loadRsi(pendingCoin)
        }
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

    override fun onSentimentError() {
        binding.sentiment.visibility = View.GONE
        binding.noSentimentResults.visibility = View.VISIBLE
    }

    override fun setRsi(rsi: RsiEntity) {
        with(binding) {
            stopLoss.text = resources.getString(R.string.possible_sl, converter.convertPrice(rsi.stopLoss), String.format("%.2f", rsi.possibleSLPerc))
            rsiStrength.text = resources.getString(R.string.rsi_strength, String.format("%.2f", rsi.signalStrength))
            rsiStrength.setTextColor(rsi.indicatorColor)
            target.text = resources.getString(R.string.possible_target, converter.convertPrice(rsi.possibleTarget), String.format("%.2f", rsi.possibleTargetPerc))
            if(rsi.isPositive){
                buySellTextRsi.text = getString(R.string.buy_text)
            }else {
                buySellTextRsi.text = getString(R.string.sell_text)
            }
            buySellTextRsi.setTextColor(rsi.indicatorColor)
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
        navController = findNavController()
        return CoinDataFragmentBinding.inflate(inflater, container, false)
    }

    override fun onErrorHandleClick() {
        pendingCoin?.let { presenter.loadData(it) }
    }
}