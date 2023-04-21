package com.ithirteeng.features.episode.domain.usecase

import com.ithirteeng.features.episode.domain.repository.EpisodeRepository
import com.ithirteeng.shared.movies.entity.MovieEntity

class GetMovieInfoUseCase(
    private val repository: EpisodeRepository,
) {
    suspend operator fun invoke(movieId: String): Result<MovieEntity> =
        repository.getMovieInfo(movieId)
}