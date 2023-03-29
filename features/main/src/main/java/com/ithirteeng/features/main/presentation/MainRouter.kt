package com.ithirteeng.features.main.presentation

import com.ithirteeng.shared.movies.utils.MoviesListType

interface MainRouter {
    fun navigateToMovieScreen(movieId: String, moviesListType: MoviesListType)
}