package com.ithirteeng.features.profile.di

import com.ithirteeng.features.profile.data.api.ProfileApi
import com.ithirteeng.features.profile.data.datasource.ProfileRemoteDatasource
import com.ithirteeng.features.profile.data.datasource.ProfileRemoteDatasourceImpl
import com.ithirteeng.features.profile.data.repository.ProfileRepositoryImpl
import com.ithirteeng.features.profile.domain.repository.ProfileRepository
import com.ithirteeng.features.profile.domain.usecase.GetProfileDataUseCase
import com.ithirteeng.features.profile.domain.usecase.UploadUserAvatarUseCase
import com.ithirteeng.features.profile.presentation.ProfileFragmentViewModel
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import com.ithirteeng.shared.network.retrofitservice.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val profileModule = module {
    single { createRetrofitService<ProfileApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<ProfileRemoteDatasource> { ProfileRemoteDatasourceImpl(api = get()) }
    factory<ProfileRepository> { ProfileRepositoryImpl(remoteDatasource = get()) }

    factory { GetProfileDataUseCase(repository = get()) }
    factory { UploadUserAvatarUseCase(repository = get()) }

    viewModel {
        ProfileFragmentViewModel(
            application = get(),
            getProfileDataUseCase = get(),
            getLocalUserDataUseCase = get(),
            removeTokenFromLocalStorageUseCase = get(),
            uploadUserAvatarUseCase = get(),
            clearUserDataLocallyUseCase = get(),
            saveUserDataLocallyUseCase = get(),
            router = get()
        )
    }
}