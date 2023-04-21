package com.ithirteeng.shared.userstorage.domain.usecase

import com.ithirteeng.shared.userstorage.domain.repository.UserRepository

class GetCurrentUserEmailUseCase(
    private val repository: UserRepository,
) {
    operator fun invoke(): String =
        repository.getCurrentUserEmail()
}