package com.ithirteeng.cinemaproject.navigation.routers

import androidx.navigation.NavController
import com.ithirteeng.cinemaproject.R
import com.ithirteeng.features.entry.login.presentation.LoginRouter

class LoginRouterImpl(private val navController: NavController?) : LoginRouter {
    override fun navigateToRegistrationFragment() {
        navController?.navigate(R.id.action_loginFragment_to_registrationFragment)
    }

    override fun navigateToMainHostScreen() {
        navController?.navigate(R.id.action_loginFragment_to_mainHostFragment)
    }
}