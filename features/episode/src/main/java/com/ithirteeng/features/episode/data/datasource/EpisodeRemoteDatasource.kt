package com.ithirteeng.features.episode.data.datasource

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

interface EpisodeRemoteDatasource {
    suspend fun getMovieEpisodes(movieId: String): List<EpisodeEntity>

    suspend fun getEpisodeTime(episodeId: String): String

    suspend fun setEpisodeTime(episodeId: String, time: String)

    suspend fun addMovieToCollection(movieId: String, collectionId: String)

    suspend fun getCollectionsList(): List<CollectionEntity>

    suspend fun getMoviesList(): List<MovieEntity>
}