package com.ithirteeng.features.collections.data.datasource

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

interface CollectionsRemoteDatasource {
    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): CollectionEntity

    suspend fun getCollectionsList(): List<CollectionEntity>

    suspend fun getCollectionMoviesList(collectionId: String): List<MovieEntity>
}