package com.ithirteeng.features.movieinfo.domain.repository

import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

interface MovieRepository {
    suspend fun getMoviesList(): Result<List<MovieEntity>>

    suspend fun getMovieEpisodes(movieId: String): Result<List<EpisodeEntity>>
}