package com.ithirteeng.features.movieinfo.domain.repository

import com.ithirteeng.shared.movies.entity.EpisodeEntity

interface MovieRepository {
    suspend fun getMovieEpisodes(movieId: String): Result<List<EpisodeEntity>>
}