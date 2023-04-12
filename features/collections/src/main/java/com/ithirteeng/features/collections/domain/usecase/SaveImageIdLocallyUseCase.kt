package com.ithirteeng.features.collections.domain.usecase

import com.ithirteeng.features.collections.domain.repository.CollectionsRepository

class SaveImageIdLocallyUseCase(
    private val repository: CollectionsRepository
) {
    operator fun invoke(imageId: Int) =
        repository.saveImageId(imageId)
}