package com.ithirteeng.features.main.domain.usecase

import com.ithirteeng.features.main.domain.repository.MainRepository
import com.ithirteeng.shared.movies.entity.EpisodeEntity

class GetMovieEpisodesListUseCase(
    private val repository: MainRepository,
) {
    suspend operator fun invoke(movieId: String): Result<List<EpisodeEntity>> =
        repository.getMovieEpisodesList(movieId)
}