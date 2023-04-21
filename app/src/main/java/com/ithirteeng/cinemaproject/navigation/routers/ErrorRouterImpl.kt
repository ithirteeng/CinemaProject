package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.errorhandler.presentation.ErrorRouter
import com.ithirteeng.features.entry.login.ui.LoginFragment

class ErrorRouterImpl(
    private val router: Router,
) : ErrorRouter {
    override fun navigateToLoginScreen() {
        router.newRootScreen(LoginFragment.provideLoginScreen)
    }

}