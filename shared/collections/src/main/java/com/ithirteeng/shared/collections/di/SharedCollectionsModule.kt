package com.ithirteeng.shared.collections.di

import com.ithirteeng.shared.collections.data.repository.SharedCollectionRepositoryImpl
import com.ithirteeng.shared.collections.data.storage.CollectionSharedPreferences
import com.ithirteeng.shared.collections.data.storage.provideDatabase
import com.ithirteeng.shared.collections.datasource.SharedCollectionsLocalDatasource
import com.ithirteeng.shared.collections.datasource.SharedCollectionsLocalDatasourceImpl
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository
import com.ithirteeng.shared.collections.domain.usecase.*
import org.koin.dsl.module

val sharedCollectionsModule = module {
    single { CollectionSharedPreferences(context = get()) }
    single { provideDatabase(application = get()) }

    factory<SharedCollectionsLocalDatasource> {
        SharedCollectionsLocalDatasourceImpl(
            database = get(),
            sharedPreferences = get()
        )
    }
    factory<SharedCollectionRepository> { SharedCollectionRepositoryImpl(localDatasource = get()) }

    factory { DeleteCollectionLocallyUseCase(repository = get()) }
    factory { UpdateCollectionLocallyUseCase(repository = get()) }
    factory { GetCollectionByIdUseCase(repository = get()) }
    factory { SaveCollectionLocallyUseCase(repository = get()) }
    factory { GetCreationFlagUseCase(repository = get()) }
    factory { SetCreationFavouritesFlagUseCase(repository = get()) }
}