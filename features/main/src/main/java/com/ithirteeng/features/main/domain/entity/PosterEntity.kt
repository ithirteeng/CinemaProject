package com.ithirteeng.features.main.domain.entity

import com.google.gson.annotations.SerializedName

data class PosterEntity(
    @SerializedName("backgroundImage")
    val backgroundImage: String,
    @SerializedName("foregroundImage")
    val foregroundImage: String,
)
