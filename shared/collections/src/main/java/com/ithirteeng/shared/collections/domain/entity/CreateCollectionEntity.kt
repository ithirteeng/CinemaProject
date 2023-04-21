package com.ithirteeng.shared.collections.domain.entity

import com.google.gson.annotations.SerializedName

data class CreateCollectionEntity(
    @SerializedName("name")
    val name: String,
)