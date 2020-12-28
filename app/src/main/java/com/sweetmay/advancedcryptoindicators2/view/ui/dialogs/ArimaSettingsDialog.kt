package com.sweetmay.advancedcryptoindicators2.view.ui.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.model.settings.ISettings

class ArimaSettingsDialog : DialogFragment() {

    private lateinit var listener: ArimaCallBack
    private lateinit var settings: ISettings

    companion object{
        fun getInstance(listener: ArimaCallBack, settings: ISettings): DialogFragment{
            val instance = ArimaSettingsDialog()
            instance.settings = settings
            instance.listener = listener
            return instance
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            val layout = inflater.inflate(R.layout.arima_settings_dialog, null)

            val timeFrame = layout.findViewById<MaterialAutoCompleteTextView>(R.id.time_frame)
            val predictionPeriod = layout.findViewById<TextInputEditText>(R.id.prediction_period)

            timeFrame.setText(settings.getArimaTimeFrameRes())

            predictionPeriod.setText(settings.arimaPredictionPeriod.toString())

            ArrayAdapter.createFromResource(layout.context, R.array.time_frame_arima, android.R.layout.simple_spinner_item).also {adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                timeFrame.setAdapter(adapter)
            }

            builder.setView(layout)

            builder.setPositiveButton(getString(R.string.positive_dialog_button)) {
                dialog, which ->
                when(timeFrame.text.toString()){
                    getString(R.string._7_days)-> settings.arimaTimeFrame = "7"
                    getString(R.string._14_days)-> settings.arimaTimeFrame = "14"
                    getString(R.string._30_days)-> settings.arimaTimeFrame = "30"
                    getString(R.string._365_days)-> settings.arimaTimeFrame = "365"
                    getString(R.string._Max)-> settings.arimaTimeFrame = "max"
                }
                settings.arimaPredictionPeriod = predictionPeriod.text.toString().toInt()
                settings.saveSettings()
                listener.saveArimaSettings()
            }

            builder.setNegativeButton(getString(R.string.negative_dialog_button)) {
                dialog, which ->
                //nothing to do
            }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}