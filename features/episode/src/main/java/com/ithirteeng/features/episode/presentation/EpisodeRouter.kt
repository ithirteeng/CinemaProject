package com.ithirteeng.features.episode.presentation

interface EpisodeRouter {

    fun exit()

    fun navigateToChatScreen(chatId: String, chatName: String)
}