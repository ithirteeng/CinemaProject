package com.ithirteeng.features.collections.data.datasource

import com.ithirteeng.features.collections.data.api.CollectionsApi
import com.ithirteeng.features.collections.domain.entity.MovieIdEntity
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

class CollectionsRemoteDatasourceImpl(
    private val api: CollectionsApi,
) : CollectionsRemoteDatasource {

    override suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): CollectionEntity =
        api.createCollection(createCollectionEntity)

    override suspend fun getCollectionsList(): List<CollectionEntity> =
        api.getCollectionsList()

    override suspend fun getCollectionMoviesList(collectionId: String): List<MovieEntity> =
        api.getCollectionMoviesList(collectionId)

    override suspend fun addMovieToCollection(collectionId: String, movieId: MovieIdEntity) =
        api.addMovieToCollection(collectionId, movieId)

    override suspend fun deleteMovieFromCollection(collectionId: String, movieId: MovieIdEntity) =
        api.deleteMovieFromCollection(collectionId, movieId)
}