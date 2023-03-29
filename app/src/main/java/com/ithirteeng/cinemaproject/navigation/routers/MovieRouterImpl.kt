package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.movieinfo.presentation.MovieRouter

class MovieRouterImpl(
    private val router: Router
): MovieRouter {
    override fun navigateToEpisodeScreen(episodeId: String) {
        //TODO: navigateToEpisodeScreen
    }

    override fun navigateToChatScreen(chatId: String) {
        //TODO: navigateToChatScreen
    }
}