package com.ithirteeng.features.collections.domain.repository

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

interface CollectionsRepository {
    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity>

    suspend fun getCollectionsList(): Result<List<CollectionEntity>>

    suspend fun getCollectionMoviesList(collectionId: String): Result<List<MovieEntity>>
}