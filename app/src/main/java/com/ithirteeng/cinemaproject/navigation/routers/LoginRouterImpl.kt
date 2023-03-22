package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.entry.login.presentation.LoginRouter
import com.ithirteeng.features.entry.registration.ui.RegistrationFragment

class LoginRouterImpl(private val router: Router) : LoginRouter {
    override fun navigateToRegistrationFragment() {
        router.navigateTo(RegistrationFragment.provideRegistrationScreen)
    }

    override fun navigateToMainHostScreen() {
        TODO("Not yet implemented")
    }

    override fun exit() {
        router.exit()
    }

}