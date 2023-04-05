package com.ithirteeng.shared.userstorage.domain.entity

data class UserEntity(
    val fullName: String?,
    val email: String?,
    var image: String? = null
) : java.io.Serializable
