package com.ithirteeng.features.compilation.data.api

import com.ithirteeng.shared.movies.entity.MovieEntity
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CompilationApi {

    @GET("movies")
    suspend fun getMoviesList(@Query("filter") moviesListType: String): List<MovieEntity>

    @POST("movies/{movieId}/dislike")
    suspend fun deleteMovieFromCompilation(@Path("movieId") movieId: String)

}