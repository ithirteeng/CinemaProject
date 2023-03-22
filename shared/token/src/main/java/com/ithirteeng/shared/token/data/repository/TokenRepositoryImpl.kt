package com.ithirteeng.shared.token.data.repository

import com.ithirteeng.shared.token.data.datasource.LocalTokenDatasource
import com.ithirteeng.shared.token.data.datasource.RemoteTokenDatasource
import com.ithirteeng.shared.token.domain.entity.TokenEntity
import com.ithirteeng.shared.token.domain.repository.TokenRepository

class TokenRepositoryImpl(
    private val localDatasource: LocalTokenDatasource,
    private val remoteDatasource: RemoteTokenDatasource
) : TokenRepository {
    override suspend fun refreshToken(refreshToken: String?): Result<TokenEntity> {
        return try {
            Result.success(remoteDatasource.refreshToken(refreshToken))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override fun saveTokensToLocalStorage(tokenEntity: TokenEntity?) =
        localDatasource.saveTokenToLocalStorage(tokenEntity)

    override fun getTokensFromLocalStorage(): TokenEntity? =
        localDatasource.getTokenFromLocalStorage()

    override fun checkIfTokensExist(): Boolean =
        localDatasource.checkTokenExistence()

    override fun removeTokenFromLocalStorage() =
        localDatasource.removeTokenFromLocalStorage()
}