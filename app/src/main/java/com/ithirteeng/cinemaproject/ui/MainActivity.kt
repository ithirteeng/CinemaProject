package com.ithirteeng.cinemaproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.ithirteeng.cinemaproject.R
import com.ithirteeng.design.GLOBAL_ROUTER
import com.ithirteeng.features.splash.ui.SplashFragment
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    private val router: Router by inject(named(GLOBAL_ROUTER))
    private val navigationHolder: NavigatorHolder by inject(named(GLOBAL_ROUTER))
    private val navigator = AppNavigator(this, R.id.container)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        router.newRootScreen(SplashFragment.provideSplashScreen)
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }
}