package com.ithirteeng.features.entry.registration.data.datasource

import com.ithirteeng.features.entry.registration.domain.entity.RegistrationEntity
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.token.domain.entity.TokenEntity

interface RegistrationRemoteDatasource {
    suspend fun postRegistrationData(registrationEntity: RegistrationEntity): TokenEntity

    suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): CollectionEntity
}