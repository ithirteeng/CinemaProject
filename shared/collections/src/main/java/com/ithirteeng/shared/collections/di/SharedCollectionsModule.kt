package com.ithirteeng.shared.collections.di

import com.ithirteeng.shared.collections.data.datasource.SharedCollectionsLocalDatasource
import com.ithirteeng.shared.collections.data.datasource.SharedCollectionsLocalDatasourceImpl
import com.ithirteeng.shared.collections.data.repository.SharedCollectionRepositoryImpl
import com.ithirteeng.shared.collections.data.storage.provideDatabase
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository
import com.ithirteeng.shared.collections.domain.usecase.*
import org.koin.dsl.module

val sharedCollectionsModule = module {
    single { provideDatabase(application = get()) }

    factory<SharedCollectionsLocalDatasource> {
        SharedCollectionsLocalDatasourceImpl(
            database = get()
        )
    }
    factory<SharedCollectionRepository> { SharedCollectionRepositoryImpl(localDatasource = get()) }

    factory { DeleteCollectionLocallyUseCase(repository = get()) }
    factory { UpdateCollectionLocallyUseCase(repository = get()) }
    factory { GetCollectionByIdUseCase(repository = get()) }
    factory { UpsertCollectionLocallyUseCase(repository = get()) }
    factory { GetFavouritesCollectionUseCase(repository = get()) }
    factory { GetCollectionsListUseCase(repository = get()) }
}