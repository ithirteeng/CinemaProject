package com.ithirteeng.features.collections.di

import com.ithirteeng.features.collections.data.api.CollectionsApi
import com.ithirteeng.features.collections.data.datasource.CollectionsRemoteDatasource
import com.ithirteeng.features.collections.data.datasource.CollectionsRemoteDatasourceImpl
import com.ithirteeng.features.collections.data.repository.CollectionsRepositoryImpl
import com.ithirteeng.features.collections.domain.repository.CollectionsRepository
import com.ithirteeng.features.collections.domain.usecase.CreateCollectionUseCase
import com.ithirteeng.features.collections.domain.usecase.GetCollectionsListUseCase
import com.ithirteeng.features.collections.presentation.CollectionsFragmentViewModel
import com.ithirteeng.shared.collections.domain.usecase.GetCreationFlagUseCase
import com.ithirteeng.shared.collections.domain.usecase.SetCreationFavouritesFlagUseCase
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

    factory { SetCreationFavouritesFlagUseCase(repository = get()) }
    factory { GetCreationFlagUseCase(repository = get()) }
    factory { CreateCollectionUseCase(repository = get()) }
    factory { GetCollectionsListUseCase(repository = get()) }

    viewModel {
        CollectionsFragmentViewModel(
            createCollectionUseCase = get(),
            saveCollectionLocallyUseCase = get(),
            getCollectionImageIdUseCase = get(),
            getCollectionsListUseCase = get()
        )
    }

}