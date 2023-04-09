package com.ithirteeng.features.collections.presentation.routers

interface CollectionInfoRouter {

    fun exit()

    fun navigateToMovieInfoScreen(movieId: String)

    fun navigateToChangeCollectionScreen()
}