package com.ithirteeng.features.movieinfo.data.datasource

import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

interface MovieRemoteDatasource {

    suspend fun getMoviesList(moviesListType: MoviesListType): List<MovieEntity>

    suspend fun getMovieEpisodes(movieId: String): List<EpisodeEntity>
}