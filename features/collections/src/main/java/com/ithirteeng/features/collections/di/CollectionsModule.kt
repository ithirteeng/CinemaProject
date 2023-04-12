package com.ithirteeng.features.collections.di

import com.ithirteeng.features.collections.data.api.CollectionsApi
import com.ithirteeng.features.collections.data.datasource.CollectionsLocalDatasource
import com.ithirteeng.features.collections.data.datasource.CollectionsLocalDatasourceImpl
import com.ithirteeng.features.collections.data.datasource.CollectionsRemoteDatasource
import com.ithirteeng.features.collections.data.datasource.CollectionsRemoteDatasourceImpl
import com.ithirteeng.features.collections.data.repository.CollectionsRepositoryImpl
import com.ithirteeng.features.collections.data.storage.IconSharedPreferences
import com.ithirteeng.features.collections.domain.repository.CollectionsRepository
import com.ithirteeng.features.collections.domain.usecase.*
import com.ithirteeng.features.collections.presentation.*
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import com.ithirteeng.shared.network.retrofitservice.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val collectionsModule = module {
    single { createRetrofitService<CollectionsApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }
    single { IconSharedPreferences(context = get()) }

    factory<CollectionsRemoteDatasource> { CollectionsRemoteDatasourceImpl(api = get()) }
    factory<CollectionsLocalDatasource> { CollectionsLocalDatasourceImpl(sharedPreferences = get()) }
    factory<CollectionsRepository> {
        CollectionsRepositoryImpl(
            remoteDatasource = get(),
            localDatasource = get()
        )
    }

    factory { CreateCollectionUseCase(repository = get()) }
    factory { GetCollectionsListUseCase(repository = get()) }
    factory { GetCollectionMoviesListUseCase(repository = get()) }
    factory { AddMovieToCollectionUseCase(repository = get()) }
    factory { DeleteMovieFromCollection(repository = get()) }
    factory { SaveImageIdLocallyUseCase(repository = get()) }
    factory { GetImageIdUseCase(repository = get()) }
    factory { ClearImageStorageUseCase(repository = get()) }

    viewModel(named(COLLECTION_MAIN_VIEW_MODEL)) {
        CollectionsFragmentViewModel(
            getCollectionByIdUseCase = get(),
            getCollectionsListUseCase = get(),
            application = get(),
            router = get(),
            getCurrentUserEmailUseCase = get()
        )
    }

    viewModel(named(COLLECTION_INFO_VIEW_MODEL)) {
        CollectionInfoFragmentViewModel(
            router = get(),
            getCollectionMoviesListUseCase = get()
        )
    }

    viewModel(named(COLLECTION_CHANGE_VIEW_MODEL)) {
        ChangeCollectionFragmentViewModel(
            router = get()
        )
    }

    viewModel(named(COLLECTION_CREATE_VIEW_MODEL)) {
        CreateCollectionFragmentViewModel(
            router = get(),
            createCollectionUseCase = get(),
            getImageIdUseCase = get(),
            clearImageStorageUseCase = get(),
            getCurrentUserEmailUseCase = get(),
            getCollectionsListUseCase = get(),
            saveCollectionLocallyUseCase = get()
        )
    }

    viewModel(named(CHOOSE_ICON_VIEW_MODEL)) {
        ChooseIconFragmentViewModel(
            router = get(),
            saveImageIdLocallyUseCase = get()
        )
    }

}