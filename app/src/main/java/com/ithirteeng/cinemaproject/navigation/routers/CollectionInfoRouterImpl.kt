package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.collections.presentation.routers.CollectionInfoRouter

class CollectionInfoRouterImpl(
    private val router: Router,
) : CollectionInfoRouter {

    override fun exit() {
        router.exit()
    }

    override fun navigateToMovieInfoScreen(movieId: String) {
        // router.navigateTo(MovieFragment.provideMovieScreen()
    }

    override fun navigateToChangeCollectionScreen() {
        TODO("Not yet implemented")
    }

}