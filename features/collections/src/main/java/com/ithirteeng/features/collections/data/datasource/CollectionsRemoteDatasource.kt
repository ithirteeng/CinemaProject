package com.ithirteeng.features.collections.data.datasource

import com.ithirteeng.features.collections.domain.entity.MovieIdEntity
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

interface CollectionsRemoteDatasource {
    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): CollectionEntity

    suspend fun getCollectionsList(): List<CollectionEntity>

    suspend fun getCollectionMoviesList(collectionId: String): List<MovieEntity>

    suspend fun addMovieToCollection(collectionId: String, movieId: MovieIdEntity)

    suspend fun deleteMovieFromCollection(collectionId: String, movieId: MovieIdEntity)
}