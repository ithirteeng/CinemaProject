package com.ithirteeng.features.collections.domain.usecase

import com.ithirteeng.features.collections.domain.repository.CollectionsRepository

class GetImageIdUseCase(
    private val repository: CollectionsRepository
) {
    operator fun invoke(): Int =
        repository.getImageId()
}