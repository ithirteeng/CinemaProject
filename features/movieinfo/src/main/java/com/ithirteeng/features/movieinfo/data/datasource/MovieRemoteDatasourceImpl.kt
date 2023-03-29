package com.ithirteeng.features.movieinfo.data.datasource

import com.ithirteeng.features.movieinfo.data.api.MovieApi
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

class MovieRemoteDatasourceImpl(
    private val api: MovieApi,
) : MovieRemoteDatasource {

    override suspend fun getMoviesList(): List<MovieEntity> =
        api.getMoviesList()

    override suspend fun getMovieEpisodes(movieId: String): List<EpisodeEntity> =
        api.getMovieEpisodes(movieId)
}