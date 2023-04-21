package com.ithirteeng.features.entry.login.domain.usecase

import com.ithirteeng.features.entry.login.domain.entity.LoginEntity
import com.ithirteeng.features.entry.login.domain.repository.LoginRepository
import com.ithirteeng.shared.token.domain.entity.TokenEntity

class PostLoginDataUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(loginEntity: LoginEntity): Result<TokenEntity> =
        repository.postLoginData(loginEntity)
}