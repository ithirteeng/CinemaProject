package com.ithirteeng.features.episode.domain.repository

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

interface EpisodeRepository {
    suspend fun getMovieEpisodes(movieId: String): Result<List<EpisodeEntity>>

    suspend fun getEpisodeTime(episodeId: String): Result<String>

    suspend fun setEpisodeTime(episodeId: String, time: String): Result<Unit>

    suspend fun addMovieToCollection(movieId: String, collectionId: String): Result<Unit>

    fun getEpisodeData(episodeId: String, episodesList: List<EpisodeEntity>): EpisodeEntity?

    fun setupEpisodeYears(episodesList: List<EpisodeEntity>): String?

    suspend fun getCollectionsList(): Result<List<CollectionEntity>>

    suspend fun getMovieInfo(movieId: String): Result<MovieEntity>
}