package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.chat.ui.ChatFragment
import com.ithirteeng.features.discussions.presentation.router.DiscussionsRouter

class DiscussionsRouterImpl(
    private val router: Router,
) : DiscussionsRouter {
    override fun exit() {
        router.exit()
    }

    override fun navigateToChatScreen(chatId: String, chatName: String) {
        router.navigateTo(ChatFragment.provideChatFragment(chatId, chatName))
    }
}