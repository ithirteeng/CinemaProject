package com.ithirteeng.shared.token.data.api

import com.ithirteeng.shared.network.common.AUTHORIZATION_HEADER
import com.ithirteeng.shared.token.domain.entity.TokenEntity
import retrofit2.http.Header
import retrofit2.http.POST

interface TokenApi {
    @POST("auth/refresh")
    suspend fun refreshToken(@Header(AUTHORIZATION_HEADER) refreshToken: String?): TokenEntity
}