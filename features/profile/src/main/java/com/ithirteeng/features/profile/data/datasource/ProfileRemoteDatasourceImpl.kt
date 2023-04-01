package com.ithirteeng.features.profile.data.datasource

import android.net.Uri
import com.ithirteeng.features.profile.data.api.ProfileApi
import com.ithirteeng.features.profile.domain.entity.ProfileEntity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProfileRemoteDatasourceImpl(
    private val api: ProfileApi,
) : ProfileRemoteDatasource {

    override suspend fun getProfileData(): ProfileEntity =
        api.getProfileData()

    override suspend fun changeUsersAvatar(uri: Uri) {
        val file = File(uri.path.toString())
        val requestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", file.name, requestBody)

        api.changeUsersAvatar(body)
    }
}