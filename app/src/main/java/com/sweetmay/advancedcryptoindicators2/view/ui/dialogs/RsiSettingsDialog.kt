package com.sweetmay.advancedcryptoindicators2.view.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.model.settings.ISettings

class RsiSettingsDialog private constructor(): DialogFragment(){
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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            val layout = inflater.inflate(R.layout.rsi_settings_dialog, null)

            val timeFrame = layout.findViewById<MaterialAutoCompleteTextView>(R.id.time_frame_rsi)
            val rr = layout.findViewById<MaterialAutoCompleteTextView>(R.id.risk_reward_rsi)

            timeFrame.setText(settings.getRsiTimeFrameRes())
            rr.setText(settings.getRsiRiskRewardRes())

            ArrayAdapter.createFromResource(layout.context, R.array.time_frame, android.R.layout.simple_spinner_item).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                timeFrame.setAdapter(adapter)
            }

            ArrayAdapter.createFromResource(layout.context, R.array.risk_reward, android.R.layout.simple_spinner_item).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                rr.setAdapter(adapter)
            }
            builder.setView(layout)

            onButtonsClick(builder, timeFrame, rr)

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
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