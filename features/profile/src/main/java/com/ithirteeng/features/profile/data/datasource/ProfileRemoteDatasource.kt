package com.ithirteeng.features.profile.data.datasource

import android.net.Uri
import com.ithirteeng.features.profile.domain.entity.ProfileEntity

interface ProfileRemoteDatasource {

    suspend fun getProfileData(): ProfileEntity

    suspend fun changeUsersAvatar(uri: Uri)
}