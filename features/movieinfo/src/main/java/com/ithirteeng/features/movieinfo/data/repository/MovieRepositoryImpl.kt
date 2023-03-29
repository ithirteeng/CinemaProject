package com.ithirteeng.features.movieinfo.data.repository

import com.ithirteeng.features.movieinfo.data.datasource.MovieRemoteDatasource
import com.ithirteeng.features.movieinfo.domain.repository.MovieRepository
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

class MovieRepositoryImpl(
    private val remoteDatasource: MovieRemoteDatasource,
) : MovieRepository {
    override suspend fun getMoviesList(moviesListType: MoviesListType): Result<List<MovieEntity>> {
        return try {
            Result.success(remoteDatasource.getMoviesList(moviesListType))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMovieEpisodes(movieId: String): Result<List<EpisodeEntity>> {
        return try {
            Result.success(remoteDatasource.getMovieEpisodes(movieId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}