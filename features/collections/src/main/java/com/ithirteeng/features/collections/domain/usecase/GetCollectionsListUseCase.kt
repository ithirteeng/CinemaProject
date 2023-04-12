package com.ithirteeng.features.collections.domain.usecase

import com.ithirteeng.features.collections.domain.repository.CollectionsRepository
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity

class GetCollectionsListUseCase(
    private val repository: CollectionsRepository,
) {
    suspend operator fun invoke(): Result<List<CollectionEntity>> =
        repository.getCollectionsList()
}