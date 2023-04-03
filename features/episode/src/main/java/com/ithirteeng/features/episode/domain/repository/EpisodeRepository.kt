package com.ithirteeng.features.episode.domain.repository

import com.ithirteeng.shared.movies.entity.EpisodeEntity

interface EpisodeRepository {
    suspend fun getMovieEpisodes(movieId: String): Result<List<EpisodeEntity>>

    suspend fun getEpisodeTime(episodeId: String): Result<String>

    suspend fun setEpisodeTime(episodeId: String, time: String): Result<Unit>

    fun getEpisodeData(episodeId: String, episodesList: List<EpisodeEntity>): EpisodeEntity?

    fun setupEpisodeYears(episodesList: List<EpisodeEntity>): String?
}