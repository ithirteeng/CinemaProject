package com.ithirteeng.features.collections.data.repository

import com.ithirteeng.features.collections.data.datasource.CollectionsLocalDatasource
import com.ithirteeng.features.collections.data.datasource.CollectionsRemoteDatasource
import com.ithirteeng.features.collections.domain.entity.MovieIdEntity
import com.ithirteeng.features.collections.domain.repository.CollectionsRepository
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

class CollectionsRepositoryImpl(
    private val remoteDatasource: CollectionsRemoteDatasource,
    private val localDatasource: CollectionsLocalDatasource,
) : CollectionsRepository {

    override suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity> {
        return try {
            Result.success(remoteDatasource.createCollection(createCollectionEntity))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCollectionsList(): Result<List<CollectionEntity>> {
        return try {
            Result.success(remoteDatasource.getCollectionsList())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCollectionMoviesList(collectionId: String): Result<List<MovieEntity>> {
        return try {
            Result.success(remoteDatasource.getCollectionMoviesList(collectionId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addMovieToCollection(
        collectionId: String,
        movieId: MovieIdEntity,
    ): Result<Unit> {
        return try {
            remoteDatasource.addMovieToCollection(collectionId, movieId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteMovieFromCollection(
        collectionId: String,
        movieId: MovieIdEntity,
    ): Result<Unit> {
        return try {
            remoteDatasource.deleteMovieFromCollection(collectionId, movieId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun saveImageId(imageId: Int) =
        localDatasource.saveImageId(imageId)

    override fun getImageId(): Int =
        localDatasource.getImageId()

    override fun clearStorage() =
        localDatasource.clearStorage()
}