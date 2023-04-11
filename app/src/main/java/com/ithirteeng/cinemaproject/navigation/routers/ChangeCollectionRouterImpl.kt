package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.collections.presentation.routers.ChangeCollectionRouter
import com.ithirteeng.features.collections.ui.ChooseIconFragment

class ChangeCollectionRouterImpl(
    private val router: Router,
) : ChangeCollectionRouter {
    override fun exit() {
        router.exit()
    }

    override fun navigateToChooseIconScreen() {
        router.navigateTo(ChooseIconFragment.provideChooseIconScreen)
    }

}