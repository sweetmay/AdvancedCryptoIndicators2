package com.sweetmay.advancedcryptoindicators2.presentation.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.RsiSettingsDialogBinding
import com.sweetmay.advancedcryptoindicators2.data.settings.ISettings
import com.sweetmay.advancedcryptoindicators2.presentation.ui.dialogs.base.BaseAlertDialog

class RsiSettingsDialog private constructor(): BaseAlertDialog<RsiSettingsDialogBinding>(){
    private lateinit var listener: RsiCallBack
    private lateinit var settings: ISettings

    companion object{
        fun getInstance(listener: RsiCallBack, settings: ISettings): DialogFragment{
            val instance = RsiSettingsDialog()
            instance.settings = settings
            instance.listener = listener
            return instance
        }
    }

    override fun setBinding(inflater: LayoutInflater): RsiSettingsDialogBinding {
        return RsiSettingsDialogBinding.inflate(inflater)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return activity?.let {
            with(binding){
                timeFrameRsi.setText(settings.getRsiTimeFrameRes())
                riskRewardRsi.setText(settings.getRsiRiskRewardRes())
            }

            ArrayAdapter.createFromResource(requireContext(), R.array.time_frame, android.R.layout.simple_spinner_item).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.timeFrameRsi.setAdapter(adapter)
            }

            ArrayAdapter.createFromResource(requireContext(), R.array.risk_reward, android.R.layout.simple_spinner_item).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.riskRewardRsi.setAdapter(adapter)
            }

            onButtonsClick(builder, binding.timeFrameRsi, binding.riskRewardRsi)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onStart() {
        super.onStart()
        (dialog as AlertDialog).apply{
            getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.onSurface))
            getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(requireContext(), R.color.onSurface))
        }
    }

    private fun onButtonsClick(builder: AlertDialog.Builder, timeFrame: MaterialAutoCompleteTextView, rr: MaterialAutoCompleteTextView) {
        builder.setPositiveButton(getString(R.string.positive_dialog_button)) { dialog, which ->
            when (timeFrame.text.toString()) {
                getString(R.string._7_days) -> settings.rsiTimeFrame = "7"
                getString(R.string._14_days) -> settings.rsiTimeFrame = "14"
                getString(R.string._30_days) -> settings.rsiTimeFrame = "30"
                getString(R.string._365_days) -> settings.rsiTimeFrame = "365"
                getString(R.string._Max) -> settings.rsiTimeFrame = "max"
            }
            settings.setRiskRewardByString(rr.text.toString())
            settings.saveSettings()
            listener.saveRsiSettings()
        }
        builder.setNegativeButton(getString(R.string.negative_dialog_button)) { dialog, which ->
            //nothing to do
        }
    }
}