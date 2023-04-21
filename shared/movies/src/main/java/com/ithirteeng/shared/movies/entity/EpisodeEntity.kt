package com.ithirteeng.shared.movies.entity

import com.google.gson.annotations.SerializedName

data class EpisodeEntity(
    @SerializedName("episodeId")
    val episodeId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("stars")
    val stars: List<String>,
    @SerializedName("year")
    val year: String,
    @SerializedName("images")
    val imageUrls: List<String>,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("preview")
    val preview: String,
    @SerializedName("filePath")
    val filePath: String,
)
