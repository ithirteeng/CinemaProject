package com.ithirteeng.cinemaproject.navigation.routers

import androidx.navigation.NavController
import com.ithirteeng.cinemaproject.R
import com.ithirteeng.features.entry.registration.presentation.RegistrationRouter

class RegistrationRouterImpl(private val navController: NavController) : RegistrationRouter {
    override fun navigateToLoginScreen() {
        navController.navigate(R.id.action_registrationFragment_to_loginFragment)
    }

    override fun navigateToMainHostScreen() {
        navController.navigate(R.id.action_registrationFragment_to_mainHostFragment)
    }

}