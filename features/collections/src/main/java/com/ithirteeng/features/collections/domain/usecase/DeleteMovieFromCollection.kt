package com.ithirteeng.features.collections.domain.usecase

import com.ithirteeng.features.collections.domain.entity.MovieIdEntity
import com.ithirteeng.features.collections.domain.repository.CollectionsRepository

class DeleteMovieFromCollection(
    private val repository: CollectionsRepository,
) {
    suspend operator fun invoke(collectionId: String, movieId: MovieIdEntity): Result<Unit> =
        repository.deleteMovieFromCollection(collectionId, movieId)
}