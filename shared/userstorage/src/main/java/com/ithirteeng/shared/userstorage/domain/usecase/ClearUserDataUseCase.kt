package com.ithirteeng.shared.userstorage.domain.usecase

import com.ithirteeng.shared.userstorage.domain.repository.UserRepository

class ClearUserDataUseCase(
    private val repository: UserRepository,
) {
    operator fun invoke() =
        repository.clearUserData()
}