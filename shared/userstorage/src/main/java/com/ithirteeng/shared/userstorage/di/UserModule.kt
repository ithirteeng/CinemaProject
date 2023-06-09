package com.ithirteeng.shared.userstorage.di

import com.ithirteeng.shared.userstorage.data.datasource.UserLocalDatasource
import com.ithirteeng.shared.userstorage.data.datasource.UserLocalDatasourceImpl
import com.ithirteeng.shared.userstorage.data.repository.UserRepositoryImpl
import com.ithirteeng.shared.userstorage.data.storage.UserStorage
import com.ithirteeng.shared.userstorage.domain.repository.UserRepository
import com.ithirteeng.shared.userstorage.domain.usecase.*
import org.koin.dsl.module

val userModule = module {
    single { UserStorage(context = get()) }

    factory<UserLocalDatasource> { UserLocalDatasourceImpl(storage = get()) }
    factory<UserRepository> { UserRepositoryImpl(localDatasource = get()) }

    factory { CheckIfUserEnteredAppUseCase(repository = get()) }
    factory { SetUserEntryFlagUseCase(repository = get()) }
    factory { GetLocalUserDataUseCase(repository = get()) }
    factory { SaveUserDataLocallyUseCase(repository = get()) }
    factory { ClearProfileDataLocallyUseCase(repository = get()) }
    factory { ClearUserDataUseCase(repository = get()) }
    factory { SetCurrentUserEmailUseCase(repository = get()) }
    factory { GetCurrentUserEmailUseCase(repository = get()) }
}