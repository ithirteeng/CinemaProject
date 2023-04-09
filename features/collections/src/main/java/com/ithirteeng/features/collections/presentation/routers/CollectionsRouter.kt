package com.ithirteeng.features.collections.presentation.routers

interface CollectionsRouter {
    fun exit()

    fun navigateToCollectionInfoScreen(collectionId: String)

    fun navigateToAddCollectionScreen()
}