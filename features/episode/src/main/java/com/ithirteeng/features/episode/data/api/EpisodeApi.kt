package com.ithirteeng.features.episode.data.api

import com.ithirteeng.features.episode.domain.entity.MovieIdEntity
import com.ithirteeng.features.episode.domain.entity.TimeEntity
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import retrofit2.http.*

interface EpisodeApi {

    @GET("movies/{movieId}/episodes")
    suspend fun getMovieEpisodes(@Path("movieId") movieId: String): List<EpisodeEntity>

    @GET("episodes/{episodeId}/time")
    suspend fun getEpisodeTime(@Path("episodeId") episodeId: String): TimeEntity

    @POST("episodes/{episodeId}/time")
    suspend fun setEpisodeTime(@Path("episodeId") episodeId: String, @Body time: TimeEntity)

    @POST("collections/{collectionId}/movies")
    suspend fun addMovieToCollection(
        @Body movieId: MovieIdEntity,
        @Path("collectionId") collectionId: String,
    )

    @GET("/api/collections")
    suspend fun getCollectionsList(): List<CollectionEntity>

    @GET("movies")
    suspend fun getMoviesList(@Query("filter") moviesListType: String): List<MovieEntity>
}