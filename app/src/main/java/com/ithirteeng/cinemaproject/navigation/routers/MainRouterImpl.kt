package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.main.presentation.MainRouter
import com.ithirteeng.features.movieinfo.ui.MovieFragment
import com.ithirteeng.shared.movies.utils.MoviesListType

class MainRouterImpl(
    private val router: Router,
) : MainRouter {
    override fun navigateToMovieScreen(movieId: String, moviesListType: MoviesListType) {
        router.navigateTo(MovieFragment.provideMovieScreen(movieId, moviesListType))
    }
}