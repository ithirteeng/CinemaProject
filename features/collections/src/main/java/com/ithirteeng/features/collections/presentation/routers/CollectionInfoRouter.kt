package com.ithirteeng.features.collections.presentation.routers

import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.movies.entity.MovieEntity

interface CollectionInfoRouter {

    fun exit()

    fun navigateToMovieInfoScreen(movieEntity: MovieEntity)

    fun navigateToChangeCollectionScreen(collectionEntity: LocalCollectionEntity?, imageId: Int)
}