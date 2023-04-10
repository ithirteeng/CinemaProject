package com.ithirteeng.shared.collections.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collections_table")
data class LocalCollectionEntity(
    @PrimaryKey
    val collectionId: String,
    val collectionImageId: Int,
    val collectionName: String,
    var isFavourite: Boolean,
    var userEmail: String,
)