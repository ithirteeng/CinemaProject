package com.ithirteeng.features.movieinfo.domain.usecase

import com.ithirteeng.features.movieinfo.domain.repository.MovieRepository
import com.ithirteeng.shared.movies.entity.EpisodeEntity

class GetMovieEpisodesListUseCase(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(movieId: String): Result<List<EpisodeEntity>> =
        repository.getMovieEpisodes(movieId)
}