package com.ithirteeng.features.main.domain.repository

import com.ithirteeng.features.main.domain.utils.MoviesListType
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.EpisodeViewEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

interface MainRepository {

    suspend fun getMoviesList(moviesListType: MoviesListType): Result<List<MovieEntity>>

    suspend fun getMovieEpisodesList(movieId: String): Result<List<EpisodeEntity>>

    suspend fun getHistory(): Result<List<EpisodeViewEntity>>
}