package com.ithirteeng.features.main.presentation

import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

interface MainRouter {
    fun navigateToMovieScreen(movieEntity: MovieEntity, moviesListType: MoviesListType)

    fun navigateToEpisodeScreen(
        episodeId: String,
        movieId: String,
        movieName: String,
        moviesListType: MoviesListType,
    )
}