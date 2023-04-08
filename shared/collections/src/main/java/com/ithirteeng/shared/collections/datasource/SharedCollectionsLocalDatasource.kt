package com.ithirteeng.shared.collections.datasource

import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

interface SharedCollectionsLocalDatasource {

    fun getCollectionImageIdById(id: String): Int

    fun saveCollectionLocally(localCollectionEntity: LocalCollectionEntity)

    fun updateCollectionLocally(localCollectionEntity: LocalCollectionEntity)

    fun deleteCollectionLocally(localCollectionEntity: LocalCollectionEntity)

    fun deleteCollectionLocally(collectionId: String)

    fun setCreationFavouritesFlag(creationFlag: Boolean, userName: String)

    fun getFavouritesCreationFlag(userName: String): Boolean
}