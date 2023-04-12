package com.ithirteeng.features.collections.domain.usecase

import com.ithirteeng.features.collections.domain.repository.CollectionsRepository

class DeleteCollectionUseCase(
    private val repository: CollectionsRepository,
) {
    suspend operator fun invoke(collectionId: String): Result<Unit> =
        repository.deleteCollectionById(collectionId)
}