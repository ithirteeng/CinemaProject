package com.ithirteeng.features.entry.registration.domain.entity

import com.google.gson.annotations.SerializedName

data class RegistrationEntity(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String
)
