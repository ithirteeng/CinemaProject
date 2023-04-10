package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.collections.presentation.routers.CollectionsRouter
import com.ithirteeng.features.collections.ui.CollectionInfoFragment

class CollectionsRouterImpl(
    private val router: Router,
) : CollectionsRouter {
    override fun exit() {
        router.exit()
    }

    override fun navigateToCollectionInfoScreen(collectionId: String, collectionName: String) {
        router.navigateTo(
            CollectionInfoFragment.provideCollectionInfoScreen(
                collectionId,
                collectionName
            )
        )
    }

    override fun navigateToAddCollectionScreen() {
        TODO("Not yet implemented")
    }
}