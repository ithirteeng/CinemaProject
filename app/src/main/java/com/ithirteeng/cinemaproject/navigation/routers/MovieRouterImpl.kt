package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.episode.ui.EpisodeFragment
import com.ithirteeng.features.movieinfo.presentation.MovieRouter

class MovieRouterImpl(
    private val router: Router,
) : MovieRouter {
    override fun navigateToEpisodeScreen(episodeId: String, movieId: String, movieName: String) {
        router.navigateTo(EpisodeFragment.provideEpisodeScreen(episodeId, movieId, movieName))
    }

    override fun navigateToChatScreen(chatId: String) {
        //TODO: navigateToChatScreen
    }
}