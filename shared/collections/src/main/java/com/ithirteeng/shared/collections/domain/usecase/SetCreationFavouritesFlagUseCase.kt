package com.ithirteeng.shared.collections.domain.usecase

import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository

class SetCreationFavouritesFlagUseCase(
    private val repository: SharedCollectionRepository
) {
    operator fun invoke(creationFlag: Boolean) =
        repository.setCreationFavouritesFlag(creationFlag)
}