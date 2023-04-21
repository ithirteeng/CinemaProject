package com.ithirteeng.features.entry.registration.domain.usecase

import com.ithirteeng.features.entry.registration.domain.repository.RegistrationRepository
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity

class CreateCollectionUseCase(
    private val repository: RegistrationRepository,
) {
    suspend operator fun invoke(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity> =
        repository.createCollection(createCollectionEntity)

}