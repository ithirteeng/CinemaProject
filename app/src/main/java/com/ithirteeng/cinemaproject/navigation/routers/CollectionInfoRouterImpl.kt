package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.collections.presentation.routers.CollectionInfoRouter
import com.ithirteeng.features.movieinfo.ui.MovieFragment
import com.ithirteeng.shared.movies.entity.MovieEntity

class CollectionInfoRouterImpl(
    private val router: Router,
) : CollectionInfoRouter {

    override fun exit() {
        router.exit()
    }

    override fun navigateToMovieInfoScreen(movieEntity: MovieEntity) {
        router.navigateTo(MovieFragment.provideMovieScreen(movieEntity))
    }

    override fun navigateToChangeCollectionScreen() {
        TODO("Not yet implemented")
    }

}