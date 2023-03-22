package com.ithirteeng.shared.token.domain.entity

import com.google.gson.annotations.SerializedName

data class TokenEntity(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("accessTokenExpiresIn")
    val accessTokenExpirationTime: String?,
    @SerializedName("refreshToken")
    val refreshToken: String?,
    @SerializedName("refreshTokenExpiresIn")
    val refreshTokenExpirationTime: String?
)