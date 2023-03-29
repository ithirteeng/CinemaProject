package com.ithirteeng.features.movieinfo.presentation

interface MovieRouter {

    fun navigateToEpisodeScreen(episodeId: String)

    fun navigateToChatScreen(chatId: String)
}