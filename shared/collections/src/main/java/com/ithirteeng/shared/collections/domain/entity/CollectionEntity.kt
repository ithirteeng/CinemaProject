package com.ithirteeng.shared.collections.domain.entity

import com.google.gson.annotations.SerializedName

data class CollectionEntity(
    @SerializedName("collectionId")
    val id: String,
    @SerializedName("name")
    val name: String,

    val imageId: Int,
)
