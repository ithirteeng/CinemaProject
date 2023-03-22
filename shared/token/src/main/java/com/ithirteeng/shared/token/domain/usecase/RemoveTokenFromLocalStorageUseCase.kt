package com.ithirteeng.shared.token.domain.usecase

import com.ithirteeng.shared.token.domain.repository.TokenRepository

class RemoveTokenFromLocalStorageUseCase(
    private val repository: TokenRepository
) {
    operator fun invoke() =
        repository.removeTokenFromLocalStorage()
}