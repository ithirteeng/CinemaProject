package com.ithirteeng.features.entry.login.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class LoginFragmentViewModel(
    application: Application,
    private val router: LoginRouter
) : AndroidViewModel(application) {
    fun navigateToRegistrationScreen() {
        router.navigateToRegistrationFragment()
    }

    fun navigateToMainHostScreen() {
        router.navigateToMainHostScreen()
    }

    fun exit() {
        router.exit()
    }
}