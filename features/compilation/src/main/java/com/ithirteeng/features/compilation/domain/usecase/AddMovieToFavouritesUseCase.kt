package com.ithirteeng.features.compilation.domain.usecase

import com.ithirteeng.features.compilation.domain.entity.MovieIdEntity
import com.ithirteeng.features.compilation.domain.repository.CompilationRepository

class AddMovieToFavouritesUseCase(
    private val repository: CompilationRepository,
) {
    suspend operator fun invoke(movieId: MovieIdEntity, collectionId: String) =
        repository.addMovieToFavouritesCollection(movieId, collectionId)
}