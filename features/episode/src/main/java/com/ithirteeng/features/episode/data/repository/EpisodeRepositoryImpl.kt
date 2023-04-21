package com.ithirteeng.features.episode.data.repository

import com.ithirteeng.features.episode.data.datasource.EpisodeRemoteDatasource
import com.ithirteeng.features.episode.domain.repository.EpisodeRepository
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import java.lang.NullPointerException

class EpisodeRepositoryImpl(
    private val remoteDatasource: EpisodeRemoteDatasource,
) : EpisodeRepository {
    override suspend fun getMovieEpisodes(movieId: String): Result<List<EpisodeEntity>> {
        return try {
            Result.success(remoteDatasource.getMovieEpisodes(movieId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getEpisodeTime(episodeId: String): Result<String> {
        return try {
            Result.success(remoteDatasource.getEpisodeTime(episodeId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun setEpisodeTime(episodeId: String, time: String): Result<Unit> {
        return try {
            remoteDatasource.setEpisodeTime(episodeId, time)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addMovieToCollection(movieId: String, collectionId: String): Result<Unit> {
        return try {
            remoteDatasource.addMovieToCollection(movieId, collectionId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getEpisodeData(
        episodeId: String,
        episodesList: List<EpisodeEntity>,
    ): EpisodeEntity? {
        return episodesList.find { it.episodeId == episodeId }
    }

    override fun setupEpisodeYears(episodesList: List<EpisodeEntity>): String? {
        return if (episodesList.isEmpty()) {
            null
        } else if (episodesList.size == 1) {
            "${episodesList.last().year} - ${episodesList.last().year}"
        } else {
            val list = episodesList.sortedBy { it.year }
            "${list.first().year} - ${list.last().year}"
        }
    }

    override suspend fun getCollectionsList(): Result<List<CollectionEntity>> {
        return try {
            Result.success(remoteDatasource.getCollectionsList())
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMovieInfo(movieId: String): Result<MovieEntity> {
        return try {
            val list = remoteDatasource.getMoviesList()
            val movie = list.find { it.id == movieId }
            if (movie == null) {
                Result.failure(NullPointerException("movie not found"))
            } else {
                Result.success(movie)
            }
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }
}