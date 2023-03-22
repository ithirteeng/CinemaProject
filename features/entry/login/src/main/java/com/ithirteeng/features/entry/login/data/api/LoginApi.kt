package com.ithirteeng.features.entry.login.data.api

import com.ithirteeng.features.entry.login.domain.entity.LoginEntity
import com.ithirteeng.shared.token.domain.entity.TokenEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("auth/login")
    suspend fun postLoginData(@Body loginEntity: LoginEntity): TokenEntity
}