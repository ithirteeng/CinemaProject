package com.ithirteeng.features.main.data.api

import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.EpisodeViewEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {
    @GET("movies")
    suspend fun getMoviesList(@Query("filter") movieType: String): List<MovieEntity>

    @GET("history")
    suspend fun getHistory(): List<EpisodeViewEntity>

    @GET("movie/{id}/episodes")
    suspend fun getMovieEpisodes(@Path("id") movieId: String): List<EpisodeEntity>
}