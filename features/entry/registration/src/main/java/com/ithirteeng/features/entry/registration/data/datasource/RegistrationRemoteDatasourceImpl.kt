package com.ithirteeng.features.entry.registration.data.datasource

import com.ithirteeng.features.entry.registration.data.api.RegistrationApi
import com.ithirteeng.features.entry.registration.domain.entity.RegistrationEntity
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.token.domain.entity.TokenEntity

class RegistrationRemoteDatasourceImpl(
    private val api: RegistrationApi,
) : RegistrationRemoteDatasource {
    override suspend fun postRegistrationData(registrationEntity: RegistrationEntity): TokenEntity =
        api.postRegistrationData(registrationEntity)

    override suspend fun createCollection(createCollectionEntity: CreateCollectionEntity): CollectionEntity =
        api.createCollection(createCollectionEntity)
}