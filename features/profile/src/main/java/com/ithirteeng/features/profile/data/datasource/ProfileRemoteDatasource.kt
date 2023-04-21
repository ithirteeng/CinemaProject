package com.ithirteeng.features.profile.data.datasource

import com.ithirteeng.features.profile.domain.entity.ProfileEntity

interface ProfileRemoteDatasource {

    suspend fun getProfileData(): ProfileEntity

    suspend fun changeUsersAvatar(byteArray: ByteArray)
}