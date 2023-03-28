package com.ithirteeng.features.compilation.domain.repository

import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

interface CompilationRepository {
    suspend fun getMoviesList(moviesListType: MoviesListType): Result<List<MovieEntity>>

    suspend fun deleteMovieFromCompilation(movieId: String): Result<Unit>
}