package com.ithirteeng.features.compilation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.compilation.R
import com.ithirteeng.features.compilation.databinding.FragmentCompilationBinding
import com.ithirteeng.features.compilation.ui.adapter.CardStackAdapter
import com.ithirteeng.features.compilation.ui.utils.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting

class CompilationFragment : Fragment() {

    companion object {
        val provideCompilationScreen = FragmentScreen { CompilationFragment() }
    }

    private lateinit var binding: FragmentCompilationBinding

    private val listStr = listOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6"
    )

    private lateinit var lastCardDirection: Direction

    private val adapter = CardStackAdapter()

    private val cardStackLayoutManager by lazy {
        CardStackLayoutManager(
            requireContext(),
            CardStackListener(
                onMovieCardAppeared = { onCardAppeared(it) },
                onMovieCardSwiped = { onCardSwiped(it) }
            )
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_compilation, container, false)
        binding = FragmentCompilationBinding.bind(layout)

        setupCardStackView()
        onDislikeButtonClick()
        onLikeButtonClick()

        adapter.submitList(listStr)
        return binding.root
    }

    private fun setupCardStackView() {
        cardStackLayoutManager.setMaxDegree(-20.0f)
        binding.cardStackView.layoutManager = cardStackLayoutManager
        binding.cardStackView.adapter = adapter
    }

    private fun onDislikeButtonClick() {
        binding.dislikeButton.setOnClickListener {
            val swipeSettings = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            cardStackLayoutManager.setSwipeAnimationSetting(swipeSettings)
            binding.cardStackView.swipe()
        }
    }

    private fun onLikeButtonClick() {
        binding.likeButton.setOnClickListener {
            val swipeSettings = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            cardStackLayoutManager.setSwipeAnimationSetting(swipeSettings)
            binding.cardStackView.swipe()
        }
    }

    private fun onCardSwiped(direction: Direction?) {
        if (direction == Direction.Right) {
            lastCardDirection = direction
        } else if (direction == Direction.Left) {
            lastCardDirection = direction
        }
        onCardDisappeared(cardStackLayoutManager.topPosition)
    }

    private fun onCardAppeared(position: Int) {
        binding.movieNameTextView.text = adapter.currentList[position]
    }

    private fun onCardDisappeared(position: Int) {
        if (position == listStr.size) {
            setupViewsVisibilityOnLastCardSwiped()
        }
        if (lastCardDirection == Direction.Left) {
            //TODO: send dislike request
        } else {
            //TODO: send like request
        }
    }

    private fun setupViewsVisibilityOnLastCardSwiped() {
        binding.movieNameTextView.visibility = View.GONE
        binding.cardStackView.visibility = View.GONE
        binding.likeButton.visibility = View.GONE
        binding.dislikeButton.visibility = View.GONE
        binding.playButton.visibility = View.GONE
        binding.tvImageView.visibility = View.VISIBLE
        binding.moviesOverTextView.visibility = View.VISIBLE
    }
}