package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.chat.ui.ChatFragment
import com.ithirteeng.features.episode.presentation.EpisodeRouter

class EpisodeRouterImpl(
    private val router: Router
): EpisodeRouter {
    override fun exit() {
        router.exit()
    }

    override fun navigateToChatScreen(chatId: String, chatName: String) {
        router.navigateTo(ChatFragment.provideChatFragment(chatId, chatName))
    }

}