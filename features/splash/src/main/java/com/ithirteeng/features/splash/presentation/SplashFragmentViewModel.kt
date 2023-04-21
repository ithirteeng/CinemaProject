package com.ithirteeng.features.splash.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ithirteeng.shared.token.domain.usecase.CheckTokenExistenceUseCase
import com.ithirteeng.shared.userstorage.domain.usecase.CheckIfUserEnteredAppUseCase
import com.ithirteeng.shared.userstorage.domain.usecase.SetUserEntryFlagUseCase

class SplashFragmentViewModel(
    application: Application,
    private val router: SplashRouter,
    private val checkIfUserEnteredAppUseCase: CheckIfUserEnteredAppUseCase,
    private val setUserEntryFlagUseCase: SetUserEntryFlagUseCase,
    private val checkTokenExistenceUseCase: CheckTokenExistenceUseCase
) : AndroidViewModel(application) {

    fun navigateToLoginScreen() {
        router.navigateToLogInScreen()
    }

    fun navigateToRegistrationScreen() {
        router.navigateToRegistrationScreen()
    }

    fun navigateToMainHostScreen() {
        router.navigateToMainHostScreen()
    }

    fun checkTokenExistence(): Boolean =
        checkTokenExistenceUseCase()

    fun checkIfUserEnteredTheApp(): Boolean =
        checkIfUserEnteredAppUseCase()

    fun setUserEntryFlag(flag: Boolean) =
        setUserEntryFlagUseCase(flag)

}