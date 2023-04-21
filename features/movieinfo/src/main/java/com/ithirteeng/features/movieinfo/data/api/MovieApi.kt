package com.ithirteeng.features.movieinfo.data.api

import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movies")
    suspend fun getMoviesList(@Query("filter") movieListType: String): List<MovieEntity>

    @GET("movies/{movieId}/episodes")
    suspend fun getMovieEpisodes(@Path("movieId") movieId: String): List<EpisodeEntity>
}