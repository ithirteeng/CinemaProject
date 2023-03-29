package com.ithirteeng.features.movieinfo.data.datasource

import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

interface MovieRemoteDatasource {

    suspend fun getMoviesList(): List<MovieEntity>

    suspend fun getMovieEpisodes(movieId: String): List<EpisodeEntity>
}