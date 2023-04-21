package com.ithirteeng.features.compilation.domain.entity

import com.google.gson.annotations.SerializedName

data class MovieIdEntity(
    @SerializedName("movieId")
    private val movieId: String
)
