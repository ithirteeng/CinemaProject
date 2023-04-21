package com.ithirteeng.features.episode.domain.usecase

import com.ithirteeng.features.episode.domain.repository.EpisodeRepository
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity

class GetCollectionsListUseCase(
    private val repository: EpisodeRepository,
) {
    suspend operator fun invoke(): Result<List<CollectionEntity>> =
        repository.getCollectionsList()
}