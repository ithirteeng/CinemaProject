package com.ithirteeng.features.entry.login.data.datasource

import com.ithirteeng.features.entry.login.domain.entity.LoginEntity
import com.ithirteeng.shared.token.domain.entity.TokenEntity

interface RemoteLoginDatasource {
    suspend fun postLoginData(loginEntity: LoginEntity): TokenEntity
}