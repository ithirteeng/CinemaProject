package com.ithirteeng.shared.userstorage.domain.usecase

import com.ithirteeng.shared.userstorage.domain.entity.UserEntity
import com.ithirteeng.shared.userstorage.domain.repository.UserRepository

class GetLocalUserDataUseCase(private val repository: UserRepository) {
    operator fun invoke(): UserEntity? =
        repository.getUserData()
}
