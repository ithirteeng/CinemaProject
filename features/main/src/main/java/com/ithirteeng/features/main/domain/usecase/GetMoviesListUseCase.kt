package com.ithirteeng.features.main.domain.usecase

import com.ithirteeng.features.main.domain.repository.MainRepository
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

class GetMoviesListUseCase(
    private val repository: MainRepository,
) {
    suspend operator fun invoke(moviesListType: MoviesListType): Result<List<MovieEntity>> =
        repository.getMoviesList(moviesListType)
}