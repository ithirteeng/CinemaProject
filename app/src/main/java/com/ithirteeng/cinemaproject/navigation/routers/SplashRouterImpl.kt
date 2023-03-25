package com.ithirteeng.cinemaproject.navigation.routers

import androidx.navigation.NavController
import com.ithirteeng.cinemaproject.R
import com.ithirteeng.features.splash.presentation.SplashRouter

class SplashRouterImpl(private val navController: NavController?) : SplashRouter {

    override fun navigateToLogInScreen() {
        navController?.navigate(R.id.action_splashFragment_to_loginFragment)
    }

    override fun navigateToRegistrationScreen() {
        navController?.navigate(R.id.action_splashFragment_to_registrationFragment)
    }

    override fun navigateToMainHostScreen() {
        navController?.navigate(R.id.action_splashFragment_to_mainHostFragment)
    }
}