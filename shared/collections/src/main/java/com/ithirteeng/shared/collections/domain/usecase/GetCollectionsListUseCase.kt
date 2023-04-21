package com.ithirteeng.shared.collections.domain.usecase

import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository

class GetCollectionsListUseCase(
    private val repository: SharedCollectionRepository
) {
    operator fun invoke(userEmail: String): List<LocalCollectionEntity>? =
        repository.getCollectionsByEmail(userEmail)
}