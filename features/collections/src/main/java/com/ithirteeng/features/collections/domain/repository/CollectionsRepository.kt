package com.ithirteeng.features.collections.domain.repository

import com.ithirteeng.features.collections.domain.entity.CollectionEntity
import com.ithirteeng.features.collections.domain.entity.CreateCollectionEntity

interface CollectionsRepository {

    fun setCreationFavouritesFlag(creationFlag: Boolean)

    fun getFavouritesCreationFlag(): Boolean

    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity>
}