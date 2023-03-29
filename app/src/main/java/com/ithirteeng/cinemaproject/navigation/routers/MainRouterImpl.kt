package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.main.presentation.MainRouter
import com.ithirteeng.features.movieinfo.MovieFragment

class MainRouterImpl(
    private val router: Router,
) : MainRouter {
    override fun navigateToMovieScreen(movieId: String) {
       router.navigateTo(MovieFragment.provideMovieScreen(movieId))
    }
}