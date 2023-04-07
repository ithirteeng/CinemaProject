package com.ithirteeng.features.collections.domain.entity

import com.google.gson.annotations.SerializedName

data class CreateCollectionEntity(
    @SerializedName("name")
    val name: String,
)