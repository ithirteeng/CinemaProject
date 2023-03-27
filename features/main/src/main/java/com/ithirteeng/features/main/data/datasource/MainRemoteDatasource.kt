package com.ithirteeng.features.main.data.datasource

import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.EpisodeViewEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

interface MainRemoteDatasource {

    suspend fun getMoviesList(moviesListType: MoviesListType): List<MovieEntity>

    suspend fun getMovieEpisodesList(movieId: String): List<EpisodeEntity>

    suspend fun getHistory(): List<EpisodeViewEntity>

}