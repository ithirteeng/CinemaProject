package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.chat.ui.ChatFragment
import com.ithirteeng.features.episode.ui.EpisodeFragment
import com.ithirteeng.features.movieinfo.presentation.MovieRouter
import com.ithirteeng.shared.movies.utils.MoviesListType

class MovieRouterImpl(
    private val router: Router,
) : MovieRouter {
    override fun navigateToEpisodeScreen(
        episodeId: String,
        movieId: String,
        movieName: String,
        moviesListType: MoviesListType,
    ) {
        router.navigateTo(
            EpisodeFragment.provideEpisodeScreen(
                episodeId,
                movieId,
                movieName,
                moviesListType
            )
        )
    }

    override fun navigateToChatScreen(chatId: String, chatName: String) {
        router.navigateTo(ChatFragment.provideChatFragment(chatId, chatName))
    }

    override fun exit() {
        router.exit()
    }
}