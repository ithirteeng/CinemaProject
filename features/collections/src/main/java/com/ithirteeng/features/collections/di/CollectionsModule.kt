package com.ithirteeng.features.collections.di

import com.ithirteeng.features.collections.data.api.CollectionsApi
import com.ithirteeng.features.collections.data.datasource.CollectionsRemoteDatasource
import com.ithirteeng.features.collections.data.datasource.CollectionsRemoteDatasourceImpl
import com.ithirteeng.features.collections.data.repository.CollectionsRepositoryImpl
import com.ithirteeng.features.collections.domain.repository.CollectionsRepository
import com.ithirteeng.features.collections.domain.usecase.CreateCollectionUseCase
import com.ithirteeng.features.collections.domain.usecase.GetCollectionMoviesListUseCase
import com.ithirteeng.features.collections.domain.usecase.GetCollectionsListUseCase
import com.ithirteeng.features.collections.presentation.CollectionInfoFragmentViewModel
import com.ithirteeng.features.collections.presentation.CollectionsFragmentViewModel
import com.ithirteeng.features.collections.ui.COLLECTION_INFO
import com.ithirteeng.features.collections.ui.COLLECTION_MAIN
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import com.ithirteeng.shared.network.retrofitservice.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val collectionsModule = module {
    single { createRetrofitService<CollectionsApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<CollectionsRemoteDatasource> { CollectionsRemoteDatasourceImpl(api = get()) }
    factory<CollectionsRepository> {
        CollectionsRepositoryImpl(
            remoteDatasource = get()
        )
    }

    factory { CreateCollectionUseCase(repository = get()) }
    factory { GetCollectionsListUseCase(repository = get()) }
    factory { GetCollectionMoviesListUseCase(repository = get()) }

    viewModel(named(COLLECTION_MAIN)) {
        CollectionsFragmentViewModel(
            createCollectionUseCase = get(),
            upsertCollectionLocallyUseCase = get(),
            getCollectionByIdUseCase = get(),
            getCollectionsListUseCase = get(),
            application = get(),
            router = get(),
            getCurrentUserEmailUseCase = get()
        )
    }

    viewModel(named(COLLECTION_INFO)) {
        CollectionInfoFragmentViewModel(
            router = get(),
            getCollectionMoviesListUseCase = get()
        )
    }

}