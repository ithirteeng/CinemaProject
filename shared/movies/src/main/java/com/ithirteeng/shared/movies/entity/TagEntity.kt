package com.ithirteeng.shared.movies.entity

import com.google.gson.annotations.SerializedName

data class TagEntity(
    @SerializedName("tagId")
    val tagId: String,
    @SerializedName("tagName")
    val tagName: String,
    @SerializedName("categoryName")
    val categoryName: String
)
