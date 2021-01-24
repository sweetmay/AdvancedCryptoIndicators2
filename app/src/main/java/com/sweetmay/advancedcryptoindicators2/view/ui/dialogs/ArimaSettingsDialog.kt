package com.sweetmay.advancedcryptoindicators2.view.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.ArimaSettingsDialogBinding
import com.sweetmay.advancedcryptoindicators2.model.settings.ISettings
import com.sweetmay.advancedcryptoindicators2.view.ui.dialogs.base.BaseAlertDialog

class ArimaSettingsDialog private constructor(): BaseAlertDialog<ArimaSettingsDialogBinding>() {

    lateinit var listener: ArimaCallBack
    lateinit var settings: ISettings

    companion object{
        fun getInstance(listener: ArimaCallBack, settings: ISettings): ArimaSettingsDialog{
            val instance = ArimaSettingsDialog()
            instance.settings = settings
            instance.listener = listener
            return instance
        }
    }

    override fun setBinding(inflater: LayoutInflater): ArimaSettingsDialogBinding {
        return ArimaSettingsDialogBinding.inflate(inflater)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return activity?.let {
            with(binding){
                timeFrameArima.setText(settings.getArimaTimeFrameRes())
                predictionPeriod.setText(settings.arimaPredictionPeriod.toString())
            }

            ArrayAdapter.createFromResource(requireContext(), R.array.time_frame, android.R.layout.simple_spinner_item).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.timeFrameArima.setAdapter(adapter)
            }

            onButtonsClick(builder, binding.timeFrameArima, binding.predictionPeriod)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun onButtonsClick(builder: AlertDialog.Builder, timeFrame: MaterialAutoCompleteTextView, predictionPeriod: TextInputEditText) {
        builder.setPositiveButton(getString(R.string.positive_dialog_button)) { dialog, which ->
            when (timeFrame.text.toString()) {
                getString(R.string._7_days) -> settings.arimaTimeFrame = "7"
                getString(R.string._14_days) -> settings.arimaTimeFrame = "14"
                getString(R.string._30_days) -> settings.arimaTimeFrame = "30"
                getString(R.string._365_days) -> settings.arimaTimeFrame = "365"
                getString(R.string._Max) -> settings.arimaTimeFrame = "max"
            }
            settings.arimaPredictionPeriod = predictionPeriod.text.toString().toInt()
            settings.saveSettings()
            listener.saveArimaSettings()
        }

        builder.setNegativeButton(getString(R.string.negative_dialog_button)) { dialog, which ->
            //nothing to do
        }
    }


}