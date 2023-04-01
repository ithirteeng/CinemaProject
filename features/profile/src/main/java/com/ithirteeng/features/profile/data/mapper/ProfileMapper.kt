package com.ithirteeng.features.profile.data.mapper

import com.ithirteeng.features.profile.domain.entity.ProfileEntity
import com.ithirteeng.shared.userstorage.domain.entity.UserEntity

fun ProfileEntity.toUserEntity(): UserEntity =
    UserEntity(
        fullName = "${this.firstName} ${this.lastName}",
        imageUri = this.avatar,
        email = this.email
    )