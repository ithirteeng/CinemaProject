package com.ithirteeng.shared.collections.di

import com.ithirteeng.shared.collections.data.repository.SharedCollectionRepositoryImpl
import com.ithirteeng.shared.collections.data.storage.provideDatabase
import com.ithirteeng.shared.collections.datasource.SharedCollectionsLocalDatasource
import com.ithirteeng.shared.collections.datasource.SharedCollectionsLocalDatasourceImpl
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository
import com.ithirteeng.shared.collections.domain.usecase.DeleteCollectionLocallyUseCase
import com.ithirteeng.shared.collections.domain.usecase.GetCollectionImageIdUseCase
import com.ithirteeng.shared.collections.domain.usecase.SaveCollectionLocallyUseCase
import com.ithirteeng.shared.collections.domain.usecase.UpdateCollectionLocallyUseCase
import org.koin.dsl.module

val sharedCollectionsModule = module {
    single { provideDatabase(application = get()) }

    factory<SharedCollectionsLocalDatasource> { SharedCollectionsLocalDatasourceImpl(database = get()) }
    factory<SharedCollectionRepository> { SharedCollectionRepositoryImpl(localDatasource = get()) }

    factory { DeleteCollectionLocallyUseCase(repository = get()) }
    factory { UpdateCollectionLocallyUseCase(repository = get()) }
    factory { GetCollectionImageIdUseCase(repository = get()) }
    factory { SaveCollectionLocallyUseCase(repository = get()) }
}