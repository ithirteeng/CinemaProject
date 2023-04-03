package com.ithirteeng.features.episode.domain

import com.google.gson.annotations.SerializedName

class TimeEntity(
    @SerializedName("timeInSeconds")
    val timeInSeconds: String?
)