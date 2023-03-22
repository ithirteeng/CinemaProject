package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.entry.login.presentation.LoginRouter

class LoginRouterImpl(private val router: Router): LoginRouter {
    override fun navigateToRegistrationFragment() {
        TODO("Not yet implemented")
    }

    override fun navigateToMainHostScreen() {
        TODO("Not yet implemented")
    }

    override fun exit() {
        router.exit()
    }

}