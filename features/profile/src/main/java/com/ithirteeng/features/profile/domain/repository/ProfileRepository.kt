package com.ithirteeng.features.profile.domain.repository

import android.net.Uri
import com.ithirteeng.features.profile.domain.entity.ProfileEntity

interface ProfileRepository {
    suspend fun getProfileData(): Result<ProfileEntity>

    suspend fun changeUsersAvatar(uri: Uri)
}