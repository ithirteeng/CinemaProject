package com.ithirteeng.features.entry.login.domain.repository

import com.ithirteeng.features.entry.login.domain.entity.LoginEntity
import com.ithirteeng.shared.token.domain.entity.TokenEntity

interface LoginRepository {
    suspend fun postLoginData(loginEntity: LoginEntity): Result<TokenEntity>
}