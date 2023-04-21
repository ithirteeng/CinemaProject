package com.ithirteeng.features.compilation.ui.utils

import android.view.View
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class CardStackListener(
    private val onMovieCardSwiped: (direction: Direction?) -> Unit,
    private val onMovieCardAppeared: (itemPosition: Int) -> Unit,
) : CardStackListener {
    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardSwiped(direction: Direction?) {
        onMovieCardSwiped(direction)
    }

    override fun onCardRewound() {}

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {
        onMovieCardAppeared(position)
    }

    override fun onCardDisappeared(view: View?, position: Int) {}
}