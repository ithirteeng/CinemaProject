package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.episode.ui.EpisodeFragment
import com.ithirteeng.features.main.presentation.MainRouter
import com.ithirteeng.features.movieinfo.ui.MovieFragment
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

class MainRouterImpl(
    private val router: Router,
) : MainRouter {
    override fun navigateToMovieScreen(movieEntity: MovieEntity, moviesListType: MoviesListType) {
        router.navigateTo(MovieFragment.provideMovieScreen(movieEntity, moviesListType))
    }

    override fun navigateToEpisodeScreen(
        episodeId: String,
        movieId: String,
        movieName: String,
        moviesListType: MoviesListType,
    ) {
        router.navigateTo(
            EpisodeFragment.provideEpisodeScreen(
                episodeId,
                movieId,
                movieName,
                moviesListType
            )
        )
    }
}