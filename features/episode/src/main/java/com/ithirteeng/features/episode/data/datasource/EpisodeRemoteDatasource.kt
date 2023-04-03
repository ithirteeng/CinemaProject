package com.ithirteeng.features.episode.data.datasource

import com.ithirteeng.shared.movies.entity.EpisodeEntity

interface EpisodeRemoteDatasource {
    suspend fun getMovieEpisodes(movieId: String): List<EpisodeEntity>

    suspend fun getEpisodeTime(episodeId: String): String

    suspend fun setEpisodeTime(episodeId: String, time: String)
}