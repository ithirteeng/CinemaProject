package com.ithirteeng.features.episode.data.repository

import com.ithirteeng.features.episode.data.datasource.EpisodeRemoteDatasource
import com.ithirteeng.features.episode.domain.repository.EpisodeRepository
import com.ithirteeng.shared.movies.entity.EpisodeEntity

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
}