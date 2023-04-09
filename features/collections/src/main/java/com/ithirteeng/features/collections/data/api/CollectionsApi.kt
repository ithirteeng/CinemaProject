package com.ithirteeng.features.collections.data.api

import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CollectionsApi {
    @POST("/api/collections")
    suspend fun createCollection(@Body createCollectionEntity: CreateCollectionEntity): CollectionEntity

    @GET("/api/collections")
    suspend fun getCollectionsList(): List<CollectionEntity>

    @GET("api/collections/{collectionId}/movies")
    suspend fun getCollectionMoviesList(@Path("collectionId") collectionId: String): List<MovieEntity>
}