package com.ithirteeng.features.compilation.data.repository

import com.ithirteeng.features.compilation.data.datasource.CompilationDatasource
import com.ithirteeng.features.compilation.domain.repository.CompilationRepository
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

class CompilationRepositoryImpl(
    private val remoteDatasource: CompilationDatasource,
) : CompilationRepository {

    override suspend fun getMoviesList(moviesListType: MoviesListType): Result<List<MovieEntity>> {
        return try {
            Result.success(remoteDatasource.getMoviesList(moviesListType))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun deleteMovieFromCompilation(movieId: String): Result<Unit> {
        return try {
            remoteDatasource.deleteMovieFromCompilation(movieId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}