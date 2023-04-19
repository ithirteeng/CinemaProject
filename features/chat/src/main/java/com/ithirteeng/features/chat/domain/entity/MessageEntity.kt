package com.ithirteeng.features.chat.domain.entity

import com.google.gson.annotations.SerializedName

data class MessageEntity(
    @SerializedName("messageId")
    val messageId: String,
    @SerializedName("creationDateTime")
    val creationDateTime: String,
    @SerializedName("authorId")
    val authorId: String,
    @SerializedName("authorName")
    val authorName: String,
    @SerializedName("authorAvatar")
    val authorAvatar: String,
    @SerializedName("text")
    val text: String
)
