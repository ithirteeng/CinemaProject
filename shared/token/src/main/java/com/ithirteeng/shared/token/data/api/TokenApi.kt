package com.ithirteeng.shared.token.data.api

import com.ithirteeng.shared.token.domain.entity.TokenEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApi {
    @POST("auth/refresh")
    suspend fun refreshToken(@Body refreshToken: String?): TokenEntity
}