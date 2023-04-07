package com.ithirteeng.shared.collections.domain.usecase

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository

class CreateCollectionUseCase(
    private val repository: SharedCollectionRepository,
) {
    suspend operator fun invoke(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity> =
        repository.createCollection(createCollectionEntity)

}