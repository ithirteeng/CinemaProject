package com.ithirteeng.features.collections.domain.usecase

import com.ithirteeng.features.collections.domain.repository.CollectionsRepository

class GetCreationFlagUseCase(
    private val repository: CollectionsRepository,
) {
    operator fun invoke(): Boolean =
        repository.getFavouritesCreationFlag()
}