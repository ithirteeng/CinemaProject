package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.collections.presentation.routers.ChangeCollectionRouter
import com.ithirteeng.features.collections.presentation.utils.ChooseIconReason
import com.ithirteeng.features.collections.ui.ChooseIconFragment

class ChangeCollectionRouterImpl(
    private val router: Router,
) : ChangeCollectionRouter {
    override fun exit() {
        router.exit()
    }

    override fun navigateToChooseIconScreen(chooseIconReason: ChooseIconReason) {
        router.navigateTo(ChooseIconFragment.provideChooseIconScreen(chooseIconReason))
    }

}