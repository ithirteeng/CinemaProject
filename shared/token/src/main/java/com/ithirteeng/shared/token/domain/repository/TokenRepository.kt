package com.ithirteeng.shared.token.domain.repository

import com.ithirteeng.shared.token.domain.entity.TokenEntity

interface TokenRepository {
    suspend fun refreshToken(refreshToken: String?): Result<TokenEntity>

    fun saveTokensToLocalStorage(tokenEntity: TokenEntity?)

    fun getTokensFromLocalStorage(): TokenEntity?

    fun checkIfTokensExist(): Boolean

    fun removeTokenFromLocalStorage()
}