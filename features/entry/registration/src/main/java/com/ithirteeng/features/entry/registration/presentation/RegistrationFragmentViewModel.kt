package com.ithirteeng.features.entry.registration.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class RegistrationFragmentViewModel(
    application: Application,
    private val router: RegistrationRouter
) : AndroidViewModel(application) {

    fun navigateToLoginScreen() {
        router.navigateToLoginScreen()
    }

    fun navigateToMainHostScreen() {
        router.navigateToMainHostScreen()
    }

    fun exit() {
        router.exit()
    }

}