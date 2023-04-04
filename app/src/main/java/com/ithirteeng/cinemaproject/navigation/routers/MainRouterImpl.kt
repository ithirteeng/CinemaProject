package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.episode.ui.EpisodeFragment
import com.ithirteeng.features.main.presentation.MainRouter
import com.ithirteeng.features.movieinfo.ui.MovieFragment
import com.ithirteeng.shared.movies.utils.MoviesListType

class MainRouterImpl(
    private val router: Router,
) : MainRouter {
    override fun navigateToMovieScreen(movieId: String, moviesListType: MoviesListType) {
        router.navigateTo(MovieFragment.provideMovieScreen(movieId, moviesListType))
    }

    override fun navigate(episodeId: String, movieId: String, movieName: String) {
        router.navigateTo(EpisodeFragment.provideEpisodeScreen(episodeId, movieId, movieName))
    }
}