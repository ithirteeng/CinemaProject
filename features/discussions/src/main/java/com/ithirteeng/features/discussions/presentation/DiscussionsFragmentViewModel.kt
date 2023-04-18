package com.ithirteeng.features.discussions.presentation

import androidx.lifecycle.ViewModel
import com.ithirteeng.features.discussions.domain.usecase.GetChatsListUseCase
import com.ithirteeng.features.discussions.presentation.router.DiscussionsRouter

class DiscussionsFragmentViewModel(
    private val getChatsListUseCase: GetChatsListUseCase,
    private val router: DiscussionsRouter,
) : ViewModel() {
}