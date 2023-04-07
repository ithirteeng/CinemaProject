package com.ithirteeng.features.collections.domain.usecase

import com.ithirteeng.features.collections.domain.repository.CollectionsRepository

class SetCreationFavouritesFlagUseCase(
    private val repository: CollectionsRepository,
) {
    operator fun invoke(creationFlag: Boolean, userName: String) =
        repository.setCreationFavouritesFlag(creationFlag, userName)
}