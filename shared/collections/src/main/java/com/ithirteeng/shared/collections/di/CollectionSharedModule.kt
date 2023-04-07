package com.ithirteeng.shared.collections.di

import com.ithirteeng.shared.collections.data.api.SharedCollectionApi
import com.ithirteeng.shared.collections.data.datasource.SharedCollectionLocalDatasource
import com.ithirteeng.shared.collections.data.datasource.SharedCollectionLocalDatasourceImpl
import com.ithirteeng.shared.collections.data.datasource.SharedCollectionRemoteDatasource
import com.ithirteeng.shared.collections.data.datasource.SharedCollectionRemoteDatasourceImpl
import com.ithirteeng.shared.collections.data.repository.SharedCollectionRepositoryImpl
import com.ithirteeng.shared.collections.data.storage.CollectionSharedPreferences
import com.ithirteeng.shared.collections.domain.repository.SharedCollectionRepository
import com.ithirteeng.shared.collections.domain.usecase.GetCreationFlagUseCase
import com.ithirteeng.shared.collections.domain.usecase.SetCreationFavouritesFlagUseCase
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import com.ithirteeng.shared.network.retrofitservice.createRetrofitService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val sharedCollection = module {
    single { CollectionSharedPreferences(context = get()) }
    single { createRetrofitService<SharedCollectionApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<SharedCollectionLocalDatasource> { SharedCollectionLocalDatasourceImpl(sharedPreferences = get()) }
    factory<SharedCollectionRemoteDatasource> { SharedCollectionRemoteDatasourceImpl(api = get()) }
    factory<SharedCollectionRepository> {
        SharedCollectionRepositoryImpl(
            localDatasource = get(),
            remoteDatasource = get()
        )
    }

    factory { SetCreationFavouritesFlagUseCase(repository = get()) }
    factory { GetCreationFlagUseCase(repository = get()) }

}