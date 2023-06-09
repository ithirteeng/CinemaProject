package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.collections.presentation.routers.CreateCollectionRouter
import com.ithirteeng.features.collections.presentation.utils.ChooseIconReason
import com.ithirteeng.features.collections.ui.ChooseIconFragment

class CreateCollectionRouterImpl(
    private val router: Router,
) : CreateCollectionRouter {
    override fun exit() {
        router.exit()
    }

    override fun navigateToChooseIconScreen(chooseIconReason: ChooseIconReason) {
        router.navigateTo(ChooseIconFragment.provideChooseIconScreen(chooseIconReason))
    }

}