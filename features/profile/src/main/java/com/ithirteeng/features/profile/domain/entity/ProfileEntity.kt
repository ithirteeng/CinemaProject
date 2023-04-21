package com.ithirteeng.features.profile.domain.entity

import com.google.gson.annotations.SerializedName

data class ProfileEntity(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String,
)
