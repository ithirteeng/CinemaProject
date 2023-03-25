package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.entry.login.ui.LoginFragment
import com.ithirteeng.features.entry.registration.presentation.RegistrationRouter
import com.ithirteeng.features.mainhost.MainHostFragment

class RegistrationRouterImpl(
    private val router: Router
): RegistrationRouter {
    override fun navigateToLoginScreen() {
        router.replaceScreen(LoginFragment.provideLoginScreen)
    }

    override fun navigateToMainHostScreen() {
        router.newRootScreen(MainHostFragment.providerMainHostScreen)
    }

    override fun exit() {
        router.exit()
    }
}