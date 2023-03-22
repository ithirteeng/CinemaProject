package com.ithirteeng.shared.token.domain.usecase

import com.ithirteeng.shared.token.domain.entity.TokenEntity
import com.ithirteeng.shared.token.domain.repository.TokenRepository

class RefreshTokenUseCase(
    private val repository: TokenRepository
) {
    suspend operator fun invoke(refreshToken: String?): TokenEntity? {
        var result: TokenEntity? = null
        repository.refreshToken(refreshToken)
            .onSuccess {
                result = it
            }
            .onFailure {
                result = null
            }
        return result
    }
}