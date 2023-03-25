package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.entry.login.ui.LoginFragment
import com.ithirteeng.features.mainhost.MainHostFragment
import com.ithirteeng.features.splash.presentation.SplashRouter

class SplashRouterImpl(private val router: Router) : SplashRouter {
    override fun navigateToLogInScreen() {
        router.newRootScreen(LoginFragment.provideLoginScreen)
    }

    override fun navigateToRegistrationScreen() {
        TODO("Not yet implemented")
    }

    override fun navigateToMainHostScreen() {
        router.newRootScreen(MainHostFragment.providerMainHostScreen)
    }

    override fun exit() {
        router.exit()
    }
}