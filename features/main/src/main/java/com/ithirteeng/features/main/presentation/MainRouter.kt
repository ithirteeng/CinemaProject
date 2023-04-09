package com.ithirteeng.features.main.presentation

import com.ithirteeng.shared.movies.entity.MovieEntity

interface MainRouter {
    fun navigateToMovieScreen(movieEntity: MovieEntity)

    fun navigate(episodeId: String, movieId: String, movieName: String)
}