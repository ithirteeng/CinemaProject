package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.compilation.presentation.CompilationRouter
import com.ithirteeng.features.movieinfo.ui.MovieFragment
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType

class CompilationRouterImpl(private val router: Router) : CompilationRouter {
    override fun navigateToMovieInfoScreen(movieEntity: MovieEntity) {
        router.navigateTo(MovieFragment.provideMovieScreen(movieEntity, MoviesListType.COMPILATION))
    }
}