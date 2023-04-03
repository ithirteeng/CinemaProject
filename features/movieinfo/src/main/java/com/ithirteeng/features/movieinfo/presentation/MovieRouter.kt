package com.ithirteeng.features.movieinfo.presentation

interface MovieRouter {

    fun navigateToEpisodeScreen(episodeId: String, movieId: String, movieName: String)

    fun navigateToChatScreen(chatId: String)
}