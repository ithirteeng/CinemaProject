package com.ithirteeng.features.collections.domain.usecase

import com.ithirteeng.features.collections.domain.repository.CollectionsRepository

class ClearImageStorageUseCase(
    private val repository: CollectionsRepository,
) {
    operator fun invoke() =
        repository.clearStorage()
}