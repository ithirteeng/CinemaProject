package com.ithirteeng.shared.collections.domain.usecase

import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository

class UpdateCollectionLocallyUseCase(
    private val repository: SharedCollectionRepository
) {
    operator fun invoke(localCollectionEntity: LocalCollectionEntity) =
        repository.updateCollectionLocally(localCollectionEntity)
}