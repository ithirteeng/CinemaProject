package com.ithirteeng.features.entry.login.data.repository

import android.util.Log
import com.ithirteeng.features.entry.login.data.datasource.RemoteLoginDatasource
import com.ithirteeng.features.entry.login.domain.entity.LoginEntity
import com.ithirteeng.features.entry.login.domain.repository.LoginRepository
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.network.common.API_ERROR
import com.ithirteeng.shared.token.domain.entity.TokenEntity

class LoginRepositoryImpl(
    private val remoteDatasource: RemoteLoginDatasource,
) : LoginRepository {
    override suspend fun postLoginData(loginEntity: LoginEntity): Result<TokenEntity> {
        return try {
            Result.success(remoteDatasource.postLoginData(loginEntity))
        } catch (e: Exception) {
            Log.e(API_ERROR, "Login post data", e)
            Result.failure(e)
        }
    }

    override suspend fun getCollectionsList(): Result<List<CollectionEntity>> {
        return try {
            Result.success(remoteDatasource.getCollectionsList())
        } catch (e: Exception) {
            Log.e(API_ERROR, "Login post data", e)
            Result.failure(e)
        }
    }
}