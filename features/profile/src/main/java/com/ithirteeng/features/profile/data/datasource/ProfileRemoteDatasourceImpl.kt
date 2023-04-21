package com.ithirteeng.features.profile.data.datasource

import com.ithirteeng.features.profile.data.api.ProfileApi
import com.ithirteeng.features.profile.domain.entity.ProfileEntity
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class ProfileRemoteDatasourceImpl(
    private val api: ProfileApi,
) : ProfileRemoteDatasource {

    override suspend fun getProfileData(): ProfileEntity =
        api.getProfileData()

    override suspend fun changeUsersAvatar(byteArray: ByteArray) {
        val body = byteArray.toRequestBody("image/png".toMediaType(), 0, byteArray.size)
        val part = MultipartBody.Part.createFormData("file", "image.png", body)
        api.changeUsersAvatar(part)
    }
}