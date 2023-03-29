package com.ithirteeng.features.movieinfo.domain.usecase

import com.ithirteeng.features.movieinfo.domain.repository.MovieRepository
import com.ithirteeng.shared.movies.entity.MovieEntity

class GetMoviesListUseCase(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(): Result<List<MovieEntity>> =
        repository.getMoviesList()
}