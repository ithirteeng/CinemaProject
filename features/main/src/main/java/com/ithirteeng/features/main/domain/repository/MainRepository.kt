package com.ithirteeng.features.main.domain.repository

import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.EpisodeViewEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

interface MainRepository {

    suspend fun getMoviesList(moviesListType: MoviesListType): Result<List<MovieEntity>>

    suspend fun getMovieEpisodesList(movieId: String): Result<List<EpisodeEntity>>

    suspend fun getHistory(): Result<List<EpisodeViewEntity>>
}