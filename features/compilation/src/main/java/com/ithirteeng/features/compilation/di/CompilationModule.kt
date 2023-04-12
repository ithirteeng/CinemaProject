package com.ithirteeng.features.compilation.di

import com.ithirteeng.features.compilation.data.api.CompilationApi
import com.ithirteeng.features.compilation.data.datasource.CompilationDatasource
import com.ithirteeng.features.compilation.data.datasource.CompilationDatasourceImpl
import com.ithirteeng.features.compilation.data.repository.CompilationRepositoryImpl
import com.ithirteeng.features.compilation.domain.repository.CompilationRepository
import com.ithirteeng.features.compilation.domain.usecase.AddMovieToFavouritesUseCase
import com.ithirteeng.features.compilation.domain.usecase.DeleteMovieFromCompilationUseCase
import com.ithirteeng.features.compilation.domain.usecase.GetCompilationMoviesListUseCase
import com.ithirteeng.features.compilation.presentation.CompilationFragmentViewModel
import com.ithirteeng.shared.network.common.TOKEN_NETWORK_TOOLS
import com.ithirteeng.shared.network.retrofitservice.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val compilationModule = module {
    single { createRetrofitService<CompilationApi>(retrofit = get(named(TOKEN_NETWORK_TOOLS))) }

    factory<CompilationDatasource> { CompilationDatasourceImpl(api = get()) }
    factory<CompilationRepository> { CompilationRepositoryImpl(remoteDatasource = get()) }

    factory { GetCompilationMoviesListUseCase(repository = get()) }
    factory { DeleteMovieFromCompilationUseCase(repository = get()) }
    factory { AddMovieToFavouritesUseCase(repository = get()) }

    viewModel {
        CompilationFragmentViewModel(
            application = get(),
            router = get(),
            getCompilationMoviesListUseCase = get(),
            deleteMovieFromCompilationUseCase = get(),
            getFavouritesCollectionUseCase = get(),
            addMovieToFavouritesUseCase = get(),
            getCurrentUserEmailUseCase = get()
        )
    }

}