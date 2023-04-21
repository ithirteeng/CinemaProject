package com.ithirteeng.features.entry.registration.domain.repository

import com.ithirteeng.features.entry.registration.domain.entity.RegistrationEntity
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.token.domain.entity.TokenEntity

interface RegistrationRepository {
    suspend fun postRegistrationData(registrationEntity: RegistrationEntity): Result<TokenEntity>

    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): Result<CollectionEntity>
}