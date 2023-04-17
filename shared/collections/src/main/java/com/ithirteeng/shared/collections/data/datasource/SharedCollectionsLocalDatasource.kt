package com.ithirteeng.shared.collections.data.datasource

import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

interface SharedCollectionsLocalDatasource {

    fun getCollectionById(id: String): LocalCollectionEntity?

    fun saveCollectionLocally(localCollectionEntity: LocalCollectionEntity)

    fun updateCollectionLocally(localCollectionEntity: LocalCollectionEntity)

    fun deleteCollectionLocally(localCollectionEntity: LocalCollectionEntity)

    fun deleteCollectionLocally(collectionId: String)

    fun getFavouritesCollection(userEmail: String): LocalCollectionEntity?

    fun getCollectionsByEmail(userEmail: String): List<LocalCollectionEntity>?
}