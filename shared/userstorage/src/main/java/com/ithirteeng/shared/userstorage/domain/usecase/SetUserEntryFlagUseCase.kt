package com.ithirteeng.shared.userstorage.domain.usecase

import com.ithirteeng.shared.userstorage.domain.repository.UserRepository

class SetUserEntryFlagUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(ifUserEnteredTheApp: Boolean) =
        repository.setUserEntryFlag(ifUserEnteredTheApp)
}