package com.sweetmay.advancedcryptoindicators2.presentation.ui

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.databinding.ActivityMainBinding
import com.sweetmay.advancedcryptoindicators2.presentation.callback.CallBackToChangeTheme

class MainActivity : AppCompatActivity(), CallBackToChangeTheme {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var nightMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(App.instance.SETTINGS, MODE_PRIVATE)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setThemeAccordingToPrefs(checkDarkTheme())
        setContentView(binding.root)
        initBottomNav()
    }

    private fun initBottomNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun setThemeAccordingToPrefs(nightMode: Boolean) {
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun toggleTheme() {
        if(nightMode){
        sharedPreferences.edit().putBoolean(App.instance.THEME_KEY, false).apply()
    }else {
        sharedPreferences.edit().putBoolean(App.instance.THEME_KEY, true).apply()
    }
        recreate()
    }

    private fun checkDarkTheme(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
                && !sharedPreferences.contains(App.instance.THEME_KEY)) {
            if (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
                sharedPreferences.edit().putBoolean(App.instance.THEME_KEY, true).apply()
                nightMode = true
                true
            } else {
                sharedPreferences.edit().putBoolean(App.instance.THEME_KEY, false).apply()
                nightMode = false
                false
            }
        }else {
            nightMode = sharedPreferences.getBoolean(App.instance.THEME_KEY, false)
            nightMode
        }
    }

}