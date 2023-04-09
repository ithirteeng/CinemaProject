package com.ithirteeng.features.collections.domain.usecase

import com.ithirteeng.features.collections.domain.repository.CollectionsRepository

class GetCollectionMoviesListUseCase(
    private val repository: CollectionsRepository,
) {
    suspend operator fun invoke(collectionId: String) =
        repository.getCollectionMoviesList(collectionId)
}