package com.ithirteeng.features.compilation.data.datasource

import com.ithirteeng.features.compilation.data.api.CompilationApi
import com.ithirteeng.features.compilation.domain.entity.MovieIdEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

class CompilationDatasourceImpl(
    private val api: CompilationApi,
) : CompilationDatasource {
    override suspend fun getMoviesList(moviesListType: MoviesListType): List<MovieEntity> =
        api.getMoviesList(moviesListType.type)

    override suspend fun deleteMovieFromCompilation(movieId: String) =
        api.deleteMovieFromCompilation(movieId)

    override suspend fun addMovieToFavouritesCollection(
        movieId: MovieIdEntity,
        collectionId: String,
    ) = api.addMovieToFavouritesCollection(movieId, collectionId)
}