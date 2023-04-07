package com.ithirteeng.shared.collections.domain.repository

import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

interface SharedCollectionRepository {

    fun getCollectionImageIdById(id: String): Int

    fun saveCollectionLocally(localCollectionEntity: LocalCollectionEntity)

    fun updateCollectionLocally(localCollectionEntity: LocalCollectionEntity)

    fun deleteCollectionLocally(localCollectionEntity: LocalCollectionEntity)

    fun deleteCollectionLocally(collectionId: String)
}