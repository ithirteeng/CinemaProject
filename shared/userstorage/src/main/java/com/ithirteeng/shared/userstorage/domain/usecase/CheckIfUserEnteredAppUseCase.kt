package com.ithirteeng.shared.userstorage.domain.usecase

import com.ithirteeng.shared.userstorage.domain.repository.UserRepository

class CheckIfUserEnteredAppUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(): Boolean =
        repository.checkIfUserEnteredTheApp()
}