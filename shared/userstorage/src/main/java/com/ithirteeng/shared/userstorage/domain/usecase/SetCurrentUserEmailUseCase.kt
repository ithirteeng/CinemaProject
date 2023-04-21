package com.ithirteeng.shared.userstorage.domain.usecase

import com.ithirteeng.shared.userstorage.domain.repository.UserRepository

class SetCurrentUserEmailUseCase(
    private val repository: UserRepository,
) {
    operator fun invoke(email: String) =
        repository.setCurrentUserEmail(email)
}