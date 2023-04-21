package com.ithirteeng.shared.token.domain.usecase

import com.ithirteeng.shared.token.domain.entity.TokenEntity
import com.ithirteeng.shared.token.domain.repository.TokenRepository

class GetTokenFromLocalStorageUseCase(
    private val repository: TokenRepository
) {
    operator fun invoke(): TokenEntity? =
        repository.getTokensFromLocalStorage()
}