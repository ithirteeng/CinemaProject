package com.ithirteeng.features.collections.domain.usecase

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.features.collections.domain.repository.CollectionsRepository

class CreateCollectionUseCase(
    private val repository: CollectionsRepository,
) {
    suspend operator fun invoke(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity> =
        repository.createCollection(createCollectionEntity)

}