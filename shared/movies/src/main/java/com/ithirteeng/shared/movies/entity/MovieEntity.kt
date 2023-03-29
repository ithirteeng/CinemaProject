package com.ithirteeng.shared.movies.entity

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    @SerializedName("movieId")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("age")
    val age: String,
    @SerializedName("chatInfo")
    val chatInfo: ChatEntity,
    @SerializedName("imageUrls")
    val imageUrls: List<String>,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("tags")
    val tags: List<TagEntity>,
)
