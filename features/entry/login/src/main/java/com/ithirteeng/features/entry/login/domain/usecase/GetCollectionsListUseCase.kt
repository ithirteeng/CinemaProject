package com.ithirteeng.features.entry.login.domain.usecase

import com.ithirteeng.features.entry.login.domain.repository.LoginRepository
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity

class GetCollectionsListUseCase(
    private val repository: LoginRepository,
) {
    suspend operator fun invoke(): Result<List<CollectionEntity>> =
        repository.getCollectionsList()
}