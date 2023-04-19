package com.ithirteeng.features.discussions.presentation.router

interface DiscussionsRouter {

    fun exit()

    fun navigateToChatScreen(chatId: String, chatName: String)
}