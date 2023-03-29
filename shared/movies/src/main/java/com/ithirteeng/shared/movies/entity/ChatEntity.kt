package com.ithirteeng.shared.movies.entity

import com.google.gson.annotations.SerializedName

data class ChatEntity(
    @SerializedName("chatId")
    val chatId: String,
    @SerializedName("chatName")
    val chatName: String
)