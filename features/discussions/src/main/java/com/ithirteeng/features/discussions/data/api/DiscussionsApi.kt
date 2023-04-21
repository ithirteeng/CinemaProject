package com.ithirteeng.features.discussions.data.api

import com.ithirteeng.features.discussions.domain.entity.ChatEntity
import retrofit2.http.GET

interface DiscussionsApi {
    @GET("chats")
    suspend fun getChatsList(): List<ChatEntity>
}