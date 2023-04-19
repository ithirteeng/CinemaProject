package com.ithirteeng.features.chat.data.api

import com.ithirteeng.features.chat.domain.entity.ProfileEntity
import retrofit2.http.GET

interface ChatApi {
    @GET("profile")
    suspend fun getProfile(): ProfileEntity
}