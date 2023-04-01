package com.ithirteeng.features.profile.data.api

import com.ithirteeng.features.profile.domain.entity.ProfileEntity
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ProfileApi {
    @GET("profile")
    suspend fun getProfileData(): ProfileEntity

    @Multipart
    @POST("profile/avatar")
    suspend fun changeUsersAvatar(@Part image: MultipartBody.Part)

}