package com.ithirteeng.features.episode.domain.usecase

import com.ithirteeng.features.episode.domain.repository.EpisodeRepository

class SetEpisodeTimeUseCase(
    private val repository: EpisodeRepository,
) {
    suspend operator fun invoke(episodeId: String, time: String): Result<Unit> =
        repository.setEpisodeTime(episodeId, time)
}