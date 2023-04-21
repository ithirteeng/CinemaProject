package com.ithirteeng.features.movieinfo.presentation

import com.ithirteeng.shared.movies.utils.MoviesListType

interface MovieRouter {

    fun navigateToEpisodeScreen(
        episodeId: String,
        movieId: String,
        movieName: String,
        moviesListType: MoviesListType,
    )

    fun navigateToChatScreen(chatId: String, chatName: String)

    fun exit()
}