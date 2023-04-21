package com.ithirteeng.features.compilation.domain.usecase

import com.ithirteeng.features.compilation.domain.repository.CompilationRepository

class DeleteMovieFromCompilationUseCase(
    private val repository: CompilationRepository,
) {
    suspend operator fun invoke(movieId: String): Result<Unit> =
        repository.deleteMovieFromCompilation(movieId)
}