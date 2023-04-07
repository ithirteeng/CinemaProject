package com.ithirteeng.shared.collections.domain.usecase

import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository

class GetCollectionImageIdUseCase(
    private val repository: SharedCollectionRepository,
) {
    operator fun invoke(collectionId: String): Int =
        repository.getCollectionImageIdById(collectionId)
}