package com.ithirteeng.shared.collections.domain.repository

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity

interface SharedCollectionRepository {

    fun setCreationFavouritesFlag(creationFlag: Boolean)

    fun getFavouritesCreationFlag(): Boolean

    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity>
}