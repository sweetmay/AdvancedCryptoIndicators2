package com.sweetmay.advancedcryptoindicators2.view.ui

import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.presenter.MainPresenter
import com.sweetmay.advancedcryptoindicators2.view.MainActivityView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainActivityView {

    private val presenter: MainPresenter by moxyPresenter { MainPresenter() }

    override fun initBottomNav() {
        val navController = findNavController(this, R.id.nav_host_fragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setupWithNavController(navController)
    }

}