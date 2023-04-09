package com.ithirteeng.features.compilation.presentation

import com.ithirteeng.shared.movies.entity.MovieEntity

interface CompilationRouter {
    fun navigateToMovieInfoScreen(movieEntity: MovieEntity)
}