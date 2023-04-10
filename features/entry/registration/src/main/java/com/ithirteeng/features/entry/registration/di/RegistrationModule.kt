package com.ithirteeng.features.entry.registration.di

import com.ithirteeng.features.entry.registration.data.api.RegistrationApi
import com.ithirteeng.features.entry.registration.data.datasource.RegistrationRemoteDatasource
import com.ithirteeng.features.entry.registration.data.datasource.RegistrationRemoteDatasourceImpl
import com.ithirteeng.features.entry.registration.data.repository.RegistrationRepositoryImpl
import com.ithirteeng.features.entry.registration.domain.repository.RegistrationRepository
import com.ithirteeng.features.entry.registration.domain.usecase.CreateCollectionUseCase
import com.ithirteeng.features.entry.registration.domain.usecase.PostRegistrationDataUseCase
import com.ithirteeng.features.entry.registration.presentation.RegistrationFragmentViewModel
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import com.ithirteeng.shared.network.retrofitservice.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val registrationModule = module {

    single { createRetrofitService<RegistrationApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<RegistrationRemoteDatasource> { RegistrationRemoteDatasourceImpl(api = get()) }
    factory<RegistrationRepository> { RegistrationRepositoryImpl(remoteDatasource = get()) }

    factory { PostRegistrationDataUseCase(repository = get()) }
    factory { CreateCollectionUseCase(repository = get()) }

    viewModel {
        RegistrationFragmentViewModel(
            application = get(),
            router = get(),
            postRegistrationDataUseCase = get(),
            saveTokenToLocalStorageUseCase = get(),
            validateTextFieldUseCase = get(),
            validateEmailUseCase = get(),
            validatePasswordsUseCase = get(),
            createCollectionUseCase = get(),
            upsertCollectionLocallyUseCase = get()
        )
    }
}