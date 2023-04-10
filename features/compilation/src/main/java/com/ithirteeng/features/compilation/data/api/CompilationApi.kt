package com.ithirteeng.features.compilation.data.api

import com.ithirteeng.features.compilation.domain.entity.MovieIdEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import retrofit2.http.*

interface CompilationApi {

    @GET("movies")
    suspend fun getMoviesList(@Query("filter") moviesListType: String): List<MovieEntity>

    @POST("movies/{movieId}/dislike")
    suspend fun deleteMovieFromCompilation(@Path("movieId") movieId: String)

    @POST("collections/{collectionId}/movies")
    suspend fun addMovieToFavouritesCollection(
        @Body movieId: MovieIdEntity,
        @Path("collectionId") collectionId: String,
    )

}