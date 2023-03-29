package com.ithirteeng.features.movieinfo.data.api

import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("movies")
    suspend fun getMoviesList(): List<MovieEntity>

    @GET("movie/{id}/episodes")
    suspend fun getMovieEpisodes(@Path("id") movieId: String): List<EpisodeEntity>
}