package com.ithirteeng.shared.collections.domain.usecase

import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository

class UpsertCollectionLocallyUseCase(
    private val repository: SharedCollectionRepository,
) {
    operator fun invoke(localCollectionEntity: LocalCollectionEntity) =
        repository.saveCollectionLocally(localCollectionEntity)
}