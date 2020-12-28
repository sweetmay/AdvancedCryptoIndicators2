package com.sweetmay.advancedcryptoindicators2.view.ui.dialogs.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseAlertDialog<VB: ViewBinding>: DialogFragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    lateinit var builder: AlertDialog.Builder

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = setBinding(LayoutInflater.from(context))
        return activity?.let {
            builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }

    abstract fun setBinding(inflater: LayoutInflater): VB

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}