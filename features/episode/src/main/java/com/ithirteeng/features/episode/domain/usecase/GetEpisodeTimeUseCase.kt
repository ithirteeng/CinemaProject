package com.ithirteeng.features.episode.domain.usecase

import com.ithirteeng.features.episode.domain.repository.EpisodeRepository

class GetEpisodeTimeUseCase(
    private val repository: EpisodeRepository,
) {
    suspend operator fun invoke(episodeId: String): Result<String> =
        repository.getEpisodeTime(episodeId)
}