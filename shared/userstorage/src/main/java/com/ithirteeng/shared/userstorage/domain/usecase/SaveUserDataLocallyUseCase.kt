package com.ithirteeng.shared.userstorage.domain.usecase

import com.ithirteeng.shared.userstorage.domain.entity.UserEntity
import com.ithirteeng.shared.userstorage.domain.repository.UserRepository

class SaveUserDataLocallyUseCase(private val repository: UserRepository) {
    operator fun invoke(userEntity: UserEntity) =
        repository.saveUserData(userEntity)
}