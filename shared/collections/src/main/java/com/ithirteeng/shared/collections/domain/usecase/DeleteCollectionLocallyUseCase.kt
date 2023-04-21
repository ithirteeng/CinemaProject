package com.ithirteeng.shared.collections.domain.usecase

import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository

class DeleteCollectionLocallyUseCase(
    private val repository: SharedCollectionRepository,
) {
    operator fun invoke(localCollectionEntity: LocalCollectionEntity) =
        repository.deleteCollectionLocally(localCollectionEntity)

    operator fun invoke(collectionId: String) =
        repository.deleteCollectionLocally(collectionId)
}