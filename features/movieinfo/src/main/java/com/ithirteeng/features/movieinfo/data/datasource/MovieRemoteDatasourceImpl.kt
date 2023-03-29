package com.ithirteeng.features.movieinfo.data.datasource

import com.ithirteeng.features.movieinfo.data.api.MovieApi
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

class MovieRemoteDatasourceImpl(
    private val api: MovieApi,
) : MovieRemoteDatasource {

    override suspend fun getMoviesList(moviesListType: MoviesListType): List<MovieEntity> =
        api.getMoviesList(moviesListType.type)

    override suspend fun getMovieEpisodes(movieId: String): List<EpisodeEntity> =
        api.getMovieEpisodes(movieId)
}