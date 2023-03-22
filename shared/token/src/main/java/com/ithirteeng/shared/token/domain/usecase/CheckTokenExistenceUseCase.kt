package com.ithirteeng.shared.token.domain.usecase

import com.ithirteeng.shared.token.domain.repository.TokenRepository

class CheckTokenExistenceUseCase(
    private val repository: TokenRepository
) {
    operator fun invoke(): Boolean =
        repository.checkIfTokensExist()
}