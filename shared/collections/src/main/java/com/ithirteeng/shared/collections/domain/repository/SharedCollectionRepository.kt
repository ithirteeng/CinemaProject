package com.ithirteeng.shared.collections.domain.repository

import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

interface SharedCollectionRepository {

    fun setCreationFavouritesFlag(creationFlag: Boolean, userName: String)

    fun getFavouritesCreationFlag(userName: String): Boolean

    fun getCollectionById(id: String): LocalCollectionEntity?

    fun saveCollectionLocally(localCollectionEntity: LocalCollectionEntity)

    fun updateCollectionLocally(localCollectionEntity: LocalCollectionEntity)

    fun deleteCollectionLocally(localCollectionEntity: LocalCollectionEntity)

    fun deleteCollectionLocally(collectionId: String)
}