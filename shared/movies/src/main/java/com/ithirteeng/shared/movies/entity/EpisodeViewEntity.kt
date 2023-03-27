package com.ithirteeng.shared.movies.entity

import com.google.gson.annotations.SerializedName

data class EpisodeViewEntity(
    @SerializedName("episodeId")
    val episodeId: String,
    @SerializedName("movieId")
    val movieId: String,
    @SerializedName("episodeName")
    val episodeName: String,
    @SerializedName("movieName")
    val movieName: String,
    @SerializedName("preview")
    val preview: String,
    @SerializedName("filePath")
    val filePath: String,
    @SerializedName("time")
    val time: Int
)
