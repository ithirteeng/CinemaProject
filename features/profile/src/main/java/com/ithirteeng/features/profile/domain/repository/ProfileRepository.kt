package com.ithirteeng.features.profile.domain.repository

import com.ithirteeng.features.profile.domain.entity.ProfileEntity

interface ProfileRepository {
    suspend fun getProfileData(): Result<ProfileEntity>

    suspend fun changeUsersAvatar(byteArray: ByteArray): Result<Unit>
}