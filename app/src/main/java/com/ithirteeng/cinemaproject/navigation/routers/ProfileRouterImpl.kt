package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.discussions.ui.DiscussionsFragment
import com.ithirteeng.features.entry.login.ui.LoginFragment
import com.ithirteeng.features.profile.presentation.ProfileRouter

class ProfileRouterImpl(
    private val router: Router,
) : ProfileRouter {

    override fun navigateToLoginScreen() {
        router.newRootScreen(LoginFragment.provideLoginScreen)
    }

    override fun navigateToDiscussionsScreen() {
        router.navigateTo(DiscussionsFragment.provideDiscussionsScreen)
    }


}