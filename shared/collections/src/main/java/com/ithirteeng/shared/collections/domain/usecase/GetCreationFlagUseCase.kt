package com.ithirteeng.shared.collections.domain.usecase

import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository

class GetCreationFlagUseCase(
    private val repository: SharedCollectionRepository,
) {
    operator fun invoke(userName: String): Boolean =
        repository.getFavouritesCreationFlag(userName)
}