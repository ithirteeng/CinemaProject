package com.ithirteeng.cinemaproject.navigation.routers

import com.github.terrakok.cicerone.Router
import com.ithirteeng.features.chat.presentation.router.ChatRouter

class ChatRouterImpl(
    private val router: Router,
) : ChatRouter {
    override fun exit() {
        router.exit()
    }
}