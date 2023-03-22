package com.ithirteeng.features.entry.login.data.datasource

import com.ithirteeng.features.entry.login.data.api.LoginApi
import com.ithirteeng.features.entry.login.domain.entity.LoginEntity
import com.ithirteeng.shared.token.domain.entity.TokenEntity

class RemoteLoginDatasourceImpl(
    private val api: LoginApi
) : RemoteLoginDatasource {
    override suspend fun postLoginData(loginEntity: LoginEntity): TokenEntity =
        api.postLoginData(loginEntity)
}