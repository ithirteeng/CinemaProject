package com.ithirteeng.features.entry.registration.data.api

import com.ithirteeng.features.entry.registration.domain.entity.RegistrationEntity
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.CreateCollectionEntity
import com.ithirteeng.shared.token.domain.entity.TokenEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationApi {
    @POST("/api/collections")
    suspend fun createCollection(@Body createCollectionEntity: CreateCollectionEntity): CollectionEntity

    @POST("auth/register")
    suspend fun postRegistrationData(@Body registrationEntity: RegistrationEntity): TokenEntity
}