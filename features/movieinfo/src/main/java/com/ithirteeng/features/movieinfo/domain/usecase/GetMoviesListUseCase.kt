package com.ithirteeng.features.movieinfo.domain.usecase

import com.ithirteeng.features.movieinfo.domain.repository.MovieRepository
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

class GetMoviesListUseCase(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(moviesListType: MoviesListType): Result<List<MovieEntity>> =
        repository.getMoviesList(moviesListType)
}