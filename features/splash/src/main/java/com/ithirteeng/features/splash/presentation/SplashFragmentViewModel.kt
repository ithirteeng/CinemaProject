package com.ithirteeng.features.splash.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SplashFragmentViewModel(
    application: Application,
    private val router: SplashRouter
) : AndroidViewModel(application) {

    fun navigateToLoginScreen() {
        router.navigateToLogInScreen()
    }

    fun exit() {
        router.exit()
    }
}