package com.ithirteeng.shared.collections.domain.usecase

import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository

class GetCollectionByIdUseCase(
    private val repository: SharedCollectionRepository,
) {
    operator fun invoke(collectionId: String): LocalCollectionEntity? =
        repository.getCollectionById(collectionId)
}