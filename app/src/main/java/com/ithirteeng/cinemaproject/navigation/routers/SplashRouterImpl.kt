package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.splash.presentation.SplashRouter

class SplashRouterImpl(private val router: Router) : SplashRouter {
    override fun navigateToSignInScreen() {
        TODO("Not yet implemented")
    }

    override fun navigateToSignUpScreen() {
        TODO("Not yet implemented")
    }

    override fun navigateToMovieScreen() {
        TODO("Not yet implemented")
    }

    override fun exit() {
        router.exit()
    }
}