package com.ithirteeng.shared.userstorage.domain.usecase

import com.ithirteeng.shared.userstorage.domain.repository.UserRepository

class ClearProfileDataLocallyUseCase(private val repository: UserRepository) {
    operator fun invoke() =
        repository.clearProfileData()
}