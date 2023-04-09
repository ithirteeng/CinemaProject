package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.episode.ui.EpisodeFragment
import com.ithirteeng.features.main.presentation.MainRouter
import com.ithirteeng.features.movieinfo.ui.MovieFragment
import com.ithirteeng.shared.movies.entity.MovieEntity

class MainRouterImpl(
    private val router: Router,
) : MainRouter {
    override fun navigateToMovieScreen(movieEntity: MovieEntity) {
        router.navigateTo(MovieFragment.provideMovieScreen(movieEntity))
    }

    override fun navigate(episodeId: String, movieId: String, movieName: String) {
        router.navigateTo(EpisodeFragment.provideEpisodeScreen(episodeId, movieId, movieName))
    }
}