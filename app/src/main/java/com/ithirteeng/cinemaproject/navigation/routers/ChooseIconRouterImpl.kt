package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.collections.presentation.routers.ChooseIconRouter

class ChooseIconRouterImpl(
    private val router: Router,
) : ChooseIconRouter {
    override fun exit() {
        router.exit()
    }
}