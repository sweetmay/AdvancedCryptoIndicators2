package com.sweetmay.advancedcryptoindicators2.view.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.sweetmay.advancedcryptoindicators2.R
import moxy.MvpAppCompatFragment

class ListFragment: MvpAppCompatFragment(R.layout.list_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToCoinDataFragment())
        }
    }
}