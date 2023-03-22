package com.ithirteeng.features.entry.login.data.repository

import com.ithirteeng.features.entry.login.data.datasource.RemoteLoginDatasource
import com.ithirteeng.features.entry.login.domain.entity.LoginEntity
import com.ithirteeng.features.entry.login.domain.repository.LoginRepository
import com.ithirteeng.shared.token.domain.entity.TokenEntity

class LoginRepositoryImpl(
    private val remoteDatasource: RemoteLoginDatasource
) : LoginRepository {
    override suspend fun postLoginData(loginEntity: LoginEntity): Result<TokenEntity> {
        return try {
            Result.success(remoteDatasource.postLoginData(loginEntity))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}