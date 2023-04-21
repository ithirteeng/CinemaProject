package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.collections.presentation.routers.CollectionsRouter
import com.ithirteeng.features.collections.ui.CollectionInfoFragment
import com.ithirteeng.features.collections.ui.CreateCollectionFragment
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

class CollectionsRouterImpl(
    private val router: Router,
) : CollectionsRouter {
    override fun exit() {
        router.exit()
    }

    override fun navigateToCollectionInfoScreen(localCollectionEntity: LocalCollectionEntity) {
        router.navigateTo(CollectionInfoFragment.provideCollectionInfoScreen(localCollectionEntity))
    }

    override fun navigateToAddCollectionScreen() {
        router.navigateTo(CreateCollectionFragment.provideCreateCollectionScreen())
    }
}