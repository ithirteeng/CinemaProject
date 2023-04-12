package com.ithirteeng.features.collections.data.api

import com.ithirteeng.features.collections.domain.entity.MovieIdEntity
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import retrofit2.Response
import retrofit2.http.*

interface CollectionsApi {
    @POST("/api/collections")
    suspend fun createCollection(@Body createCollectionEntity: CreateCollectionEntity): CollectionEntity

    @GET("/api/collections")
    suspend fun getCollectionsList(): List<CollectionEntity>

    @GET("collections/{collectionId}/movies")
    suspend fun getCollectionMoviesList(@Path("collectionId") collectionId: String): List<MovieEntity>

    @POST("collections/{collectionId}/movies")
    suspend fun addMovieToCollection(
        @Path("collectionId") collectionId: String,
        movieId: MovieIdEntity,
    )

    @DELETE("collections/{collectionId}/movies")
    suspend fun deleteMovieFromCollection(
        @Path("collectionId") collectionId: String,
        movieId: MovieIdEntity,
    )

    @DELETE("collections/{collectionId}")
    suspend fun deleteCollection(@Path("collectionId") collectionId: String): Response<Unit>
}