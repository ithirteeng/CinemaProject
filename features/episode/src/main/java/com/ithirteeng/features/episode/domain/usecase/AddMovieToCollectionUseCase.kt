package com.ithirteeng.features.episode.domain.usecase

import com.ithirteeng.features.episode.domain.repository.EpisodeRepository

class AddMovieToCollectionUseCase(
    private val repository: EpisodeRepository
) {
    suspend operator fun invoke(movieId: String, collectionId: String): Result<Unit> =
        repository.addMovieToCollection(movieId, collectionId)
}