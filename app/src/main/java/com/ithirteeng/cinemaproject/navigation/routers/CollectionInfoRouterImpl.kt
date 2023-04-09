package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.collections.presentation.routers.CollectionInfoRouter
import com.ithirteeng.features.movieinfo.ui.MovieFragment
import com.ithirteeng.shared.movies.utils.MoviesListType

class CollectionInfoRouterImpl(
    private val router: Router,
) : CollectionInfoRouter {

    override fun exit() {
        router.exit()
    }

    override fun navigateToMovieInfoScreen(movieId: String) {
        router.navigateTo(MovieFragment.provideMovieScreen(movieId, MoviesListType.NEW))
    }

    override fun navigateToChangeCollectionScreen() {
        TODO("Not yet implemented")
    }

}