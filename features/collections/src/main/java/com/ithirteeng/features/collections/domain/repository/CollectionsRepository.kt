package com.ithirteeng.features.collections.domain.repository

import com.ithirteeng.features.collections.domain.entity.CollectionEntity
import com.ithirteeng.features.collections.domain.entity.CreateCollectionEntity

interface CollectionsRepository {

    fun setCreationFavouritesFlag(creationFlag: Boolean, userName: String)

    fun getFavouritesCreationFlag(userName: String): Boolean

    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity>
}