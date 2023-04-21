package com.ithirteeng.features.compilation.domain.usecase

import com.ithirteeng.features.compilation.domain.repository.CompilationRepository
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

class GetCompilationMoviesListUseCase(
    private val repository: CompilationRepository,
) {
    suspend operator fun invoke(): Result<List<MovieEntity>> =
        repository.getMoviesList(MoviesListType.COMPILATION)
}