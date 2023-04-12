package com.ithirteeng.features.entry.login.di

import com.ithirteeng.features.entry.login.data.api.LoginApi
import com.ithirteeng.features.entry.login.data.datasource.RemoteLoginDatasource
import com.ithirteeng.features.entry.login.data.datasource.RemoteLoginDatasourceImpl
import com.ithirteeng.features.entry.login.data.repository.LoginRepositoryImpl
import com.ithirteeng.features.entry.login.domain.repository.LoginRepository
import com.ithirteeng.features.entry.login.domain.usecase.GetCollectionsListUseCase
import com.ithirteeng.features.entry.login.domain.usecase.PostLoginDataUseCase
import com.ithirteeng.features.entry.login.presentation.LoginFragmentViewModel
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import com.ithirteeng.shared.network.retrofitservice.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loginModule = module {

    single { createRetrofitService<LoginApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<RemoteLoginDatasource> { RemoteLoginDatasourceImpl(api = get()) }
    factory<LoginRepository> { LoginRepositoryImpl(remoteDatasource = get()) }

    factory { PostLoginDataUseCase(repository = get()) }
    factory { GetCollectionsListUseCase(repository = get()) }

    viewModel {
        LoginFragmentViewModel(
            application = get(),
            router = get(),
            postLoginDataUseCase = get(),
            saveTokenToLocalStorageUseCase = get(),
            validateEmailUseCase = get(),
            validateTextFieldUseCase = get(),
            setCurrentUserEmailUseCase = get(),
            getCollectionsListUseCase = get(),
            upsertCollectionLocallyUseCase = get(),
            getCollectionByIdUseCase = get()
        )
    }
}