package com.ithirteeng.features.episode.domain.usecase

import com.ithirteeng.features.episode.domain.repository.EpisodeRepository
import com.ithirteeng.shared.movies.entity.EpisodeEntity

class GetEpisodeUseCase(
    private val repository: EpisodeRepository,
) {
    operator fun invoke(episodeId: String, episodes: List<EpisodeEntity>): EpisodeEntity? =
        repository.getEpisodeData(episodeId, episodes)
}