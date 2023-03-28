package com.ithirteeng.features.compilation.data.datasource

import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

interface CompilationDatasource {
    suspend fun getMoviesList(moviesListType: MoviesListType): List<MovieEntity>

    suspend fun deleteMovieFromCompilation(movieId: String)
}