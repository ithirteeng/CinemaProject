package com.ithirteeng.features.episode.data.datasource

import com.ithirteeng.features.episode.data.api.EpisodeApi
import com.ithirteeng.features.episode.domain.TimeEntity
import com.ithirteeng.shared.movies.entity.EpisodeEntity

class EpisodeRemoteDatasourceImpl(
    private val api: EpisodeApi,
) : EpisodeRemoteDatasource {
    override suspend fun getMovieEpisodes(movieId: String): List<EpisodeEntity> =
        api.getMovieEpisodes(movieId)

    override suspend fun getEpisodeTime(episodeId: String): String {
        return api.getEpisodeTime(episodeId).timeInSeconds ?: "0"
    }


    override suspend fun setEpisodeTime(episodeId: String, time: String) =
        api.setEpisodeTime(episodeId, TimeEntity(time))
}