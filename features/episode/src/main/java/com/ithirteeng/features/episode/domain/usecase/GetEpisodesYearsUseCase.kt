package com.ithirteeng.features.episode.domain.usecase

import com.ithirteeng.features.episode.domain.repository.EpisodeRepository
import com.ithirteeng.shared.movies.entity.EpisodeEntity

class GetEpisodesYearsUseCase(
    private val repository: EpisodeRepository,
) {
    operator fun invoke(episodesList: List<EpisodeEntity>): String? =
        repository.setupEpisodeYears(episodesList)
}