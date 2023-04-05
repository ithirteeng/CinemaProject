package com.ithirteeng.features.profile.data.repository

import com.ithirteeng.features.profile.data.datasource.ProfileRemoteDatasource
import com.ithirteeng.features.profile.domain.entity.ProfileEntity
import com.ithirteeng.features.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val remoteDatasource: ProfileRemoteDatasource,
) : ProfileRepository {
    override suspend fun getProfileData(): Result<ProfileEntity> {
        return try {
            Result.success(remoteDatasource.getProfileData())
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }
    }

    override suspend fun changeUsersAvatar(byteArray: ByteArray): Result<Unit> {
        return try {
            remoteDatasource.changeUsersAvatar(byteArray)
            Result.success(Unit)
        } catch (e: java.lang.Exception) {
            Result.failure(e)
        }

    }

}