package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.collections.CollectionsFragment
import com.ithirteeng.features.compilation.CompilationFragment
import com.ithirteeng.features.main.ui.MainFragment
import com.ithirteeng.features.mainhost.presentation.MainHostRouter

class MainHostRouterImpl(
    private val router: Router
) : MainHostRouter {
    override fun navigateToMainScreen() {
        router.newRootScreen(MainFragment.provideMainScreen)
    }

    override fun navigateToCompilationScreen() {
        router.newRootScreen(CompilationFragment.provideCompilationScreen)
    }

    override fun navigateToProfileScreen() {
        TODO("Not yet implemented")
    }

    override fun navigateToCollectionsScreen() {
        router.newRootScreen(CollectionsFragment.provideCollectionsScreen)
    }
}