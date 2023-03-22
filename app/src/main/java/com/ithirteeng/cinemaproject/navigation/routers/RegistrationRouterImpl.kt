package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.entry.login.ui.LoginFragment
import com.ithirteeng.features.entry.registration.presentation.RegistrationRouter

class RegistrationRouterImpl(
    private val router: Router
): RegistrationRouter {
    override fun navigateToLoginScreen() {
        router.navigateTo(LoginFragment.provideLoginScreen)
    }

    override fun navigateToMainHostScreen() {
        TODO("Not yet implemented")
    }

    override fun exit() {
        router.exit()
    }
}