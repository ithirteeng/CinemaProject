package com.ithirteeng.shared.token.domain.usecase

import android.util.Log
import com.ithirteeng.shared.network.common.API_ERROR
import com.ithirteeng.shared.network.common.BEARER
import com.ithirteeng.shared.token.domain.entity.TokenEntity
import com.ithirteeng.shared.token.domain.repository.TokenRepository

class RefreshTokenUseCase(
    private val repository: TokenRepository,
) {
    suspend operator fun invoke(refreshToken: String?): TokenEntity? {
        var result: TokenEntity? = null
        repository.refreshToken("$BEARER $refreshToken")
            .onSuccess {
                result = it
            }
            .onFailure {
                result = null
                Log.e(API_ERROR, "token refresh", it)
                throw it
            }
        return result
    }
}