package com.ithirteeng.features.collections.domain.repository

import com.ithirteeng.features.collections.domain.entity.MovieIdEntity
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

interface CollectionsRepository {
    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity>

    suspend fun getCollectionsList(): Result<List<CollectionEntity>>

    suspend fun getCollectionMoviesList(collectionId: String): Result<List<MovieEntity>>

    suspend fun addMovieToCollection(collectionId: String, movieId: MovieIdEntity): Result<Unit>

    suspend fun deleteMovieFromCollection(collectionId: String, movieId: MovieIdEntity): Result<Unit>

    fun saveImageId(imageId: Int)

    fun getImageId(): Int

    fun clearStorage()
}