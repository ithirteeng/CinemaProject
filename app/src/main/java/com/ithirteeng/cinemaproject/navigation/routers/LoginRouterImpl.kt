package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.entry.login.presentation.LoginRouter
import com.ithirteeng.features.entry.registration.ui.RegistrationFragment
import com.ithirteeng.features.mainhost.MainHostFragment

class LoginRouterImpl(private val router: Router) : LoginRouter {
    override fun navigateToRegistrationFragment() {
        router.replaceScreen(RegistrationFragment.provideRegistrationScreen)
    }

    override fun navigateToMainHostScreen() {
        router.newRootScreen(MainHostFragment.providerMainHostScreen)
    }

    override fun exit() {
        router.exit()
    }

}