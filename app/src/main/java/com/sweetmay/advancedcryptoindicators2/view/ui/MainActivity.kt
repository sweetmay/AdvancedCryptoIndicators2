package com.sweetmay.advancedcryptoindicators2.view.ui

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sweetmay.advancedcryptoindicators2.databinding.ActivityMainBinding
import com.sweetmay.advancedcryptoindicators2.presenter.MainPresenter
import com.sweetmay.advancedcryptoindicators2.view.MainActivityView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainActivityView {

    private val presenter: MainPresenter by moxyPresenter { MainPresenter() }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initBottomNav() {
        val navController = findNavController(this,  binding.navHostFragment.id)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

}