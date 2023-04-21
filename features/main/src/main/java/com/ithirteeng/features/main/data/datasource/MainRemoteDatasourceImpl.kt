package com.ithirteeng.features.main.data.datasource

import com.ithirteeng.features.main.data.api.MainApi
import com.ithirteeng.features.main.domain.entity.PosterEntity
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.EpisodeViewEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

class MainRemoteDatasourceImpl(
    private val api: MainApi,
) : MainRemoteDatasource {

    override suspend fun getMoviesList(moviesListType: MoviesListType): List<MovieEntity> =
        api.getMoviesList(moviesListType.type)

    override suspend fun getMovieEpisodesList(movieId: String): List<EpisodeEntity> =
        api.getMovieEpisodes(movieId)

    override suspend fun getHistory(): List<EpisodeViewEntity> =
        api.getHistory()

    override suspend fun getPosterEntity(): PosterEntity =
        api.getMainPoster()

}