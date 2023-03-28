package com.ithirteeng.features.compilation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
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

    private fun onCardSwiped(direction: Direction?) {
        if (direction == Direction.Right) {
            // TODO: send like request
            Toast.makeText(requireContext(), "like", Toast.LENGTH_SHORT).show()
        } else if (direction == Direction.Left) {
            // TODO: send dislike request
            Toast.makeText(requireContext(), "dislike", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onCardAppeared(position: Int) {
        binding.textView.text = adapter.currentList[position]
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
            // TODO: make dislike request
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
            // TODO: make like request
        }
    }
}