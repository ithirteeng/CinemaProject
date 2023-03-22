package com.ithirteeng.features.entry.login.domain.entity

import com.google.gson.annotations.SerializedName

data class LoginEntity(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
