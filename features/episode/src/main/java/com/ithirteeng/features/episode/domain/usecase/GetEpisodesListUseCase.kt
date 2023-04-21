package com.ithirteeng.features.episode.domain.usecase

import com.ithirteeng.features.episode.domain.repository.EpisodeRepository
import com.ithirteeng.shared.movies.entity.EpisodeEntity

class GetEpisodesListUseCase(
    private val repository: EpisodeRepository,
) {
    suspend operator fun invoke(movieId: String): Result<List<EpisodeEntity>> =
        repository.getMovieEpisodes(movieId)
}