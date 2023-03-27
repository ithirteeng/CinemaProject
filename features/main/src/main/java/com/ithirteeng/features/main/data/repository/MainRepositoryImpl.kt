package com.ithirteeng.features.main.data.repository

import com.ithirteeng.features.main.data.datasource.MainRemoteDatasource
import com.ithirteeng.features.main.domain.entity.PosterEntity
import com.ithirteeng.features.main.domain.repository.MainRepository
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.EpisodeViewEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

class MainRepositoryImpl(
    private val remoteDatasource: MainRemoteDatasource,
) : MainRepository {

    override suspend fun getMoviesList(moviesListType: MoviesListType): Result<List<MovieEntity>> {
        return try {
            Result.success(remoteDatasource.getMoviesList(moviesListType))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMovieEpisodesList(movieId: String): Result<List<EpisodeEntity>> {
        return try {
            Result.success(remoteDatasource.getMovieEpisodesList(movieId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getHistory(): Result<List<EpisodeViewEntity>> {
        return try {
            Result.success(remoteDatasource.getHistory())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMainPoster(): Result<PosterEntity> {
        return try {
            Result.success(remoteDatasource.getPosterEntity())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}