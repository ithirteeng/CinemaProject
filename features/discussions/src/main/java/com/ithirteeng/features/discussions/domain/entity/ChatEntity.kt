package com.ithirteeng.features.discussions.domain.entity

import com.google.gson.annotations.SerializedName

data class ChatEntity(
    @SerializedName("chatId")
    val chatId: String,
    @SerializedName("chatName")
    val chatName: String,
    @SerializedName("lastMessage")
    val lastMessage: LastMessageEntity,
)
