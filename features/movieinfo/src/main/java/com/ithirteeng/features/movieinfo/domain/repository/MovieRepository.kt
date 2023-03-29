package com.ithirteeng.features.movieinfo.domain.repository

import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

interface MovieRepository {
    suspend fun getMoviesList(moviesListType: MoviesListType): Result<List<MovieEntity>>

    suspend fun getMovieEpisodes(movieId: String): Result<List<EpisodeEntity>>
}