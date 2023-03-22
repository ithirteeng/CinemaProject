package com.ithirteeng.shared.token.domain.usecase

import com.ithirteeng.shared.token.domain.entity.TokenEntity
import com.ithirteeng.shared.token.domain.repository.TokenRepository

class SaveTokenToLocalStorageUseCase(
    private val repository: TokenRepository
) {
    operator fun invoke(tokenEntity: TokenEntity?) {
        repository.saveTokensToLocalStorage(tokenEntity)
    }
}