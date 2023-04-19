package com.ithirteeng.features.chat.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.features.chat.presentation.router.ChatRouter

class ChatFragmentViewModel(
    private val router: ChatRouter,
) : ViewModel() {
    fun exit() {
        router.exit()
    }
}