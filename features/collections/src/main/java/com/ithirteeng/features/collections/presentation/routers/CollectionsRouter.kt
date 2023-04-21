package com.ithirteeng.features.collections.presentation.routers

import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

interface CollectionsRouter {
    fun exit()

    fun navigateToCollectionInfoScreen(localCollectionEntity: LocalCollectionEntity)

    fun navigateToAddCollectionScreen()
}