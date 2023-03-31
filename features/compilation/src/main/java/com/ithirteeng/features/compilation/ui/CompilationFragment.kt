package com.ithirteeng.features.compilation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.compilation.R
import com.ithirteeng.features.compilation.databinding.FragmentCompilationBinding
import com.ithirteeng.features.compilation.presentation.CompilationFragmentViewModel
import com.ithirteeng.features.compilation.ui.adapter.CardStackAdapter
import com.ithirteeng.features.compilation.ui.utils.CardStackListener
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import org.koin.androidx.viewmodel.ext.android.viewModel

class CompilationFragment : Fragment() {

    companion object {
        val provideCompilationScreen = FragmentScreen { CompilationFragment() }
    }

    private lateinit var binding: FragmentCompilationBinding

    private val viewModel: CompilationFragmentViewModel by viewModel()

    private lateinit var lastCardDirection: Direction

    private val cardStackAdapter = CardStackAdapter()

    private lateinit var moviesList: List<MovieEntity>

    private lateinit var cardStackLayoutManager: CardStackLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_compilation, container, false)
        binding = FragmentCompilationBinding.bind(layout)

        setupCardStackLayoutManager()

        onGettingMoviesList()
        onDislikeButtonClick()
        onLikeButtonClick()

        return binding.root
    }

    private fun onGettingMoviesList() {
        viewModel.makeGetMoviesListRequest { handleErrors(it) }

        binding.progressBar.visibility = View.VISIBLE
        binding.movieNameTextView.text = ""
        setViewsVisibility(View.GONE)

        viewModel.getMoviesListLiveData().observe(this.viewLifecycleOwner) {
            moviesList = it
            cardStackAdapter.submitList(it)
            setupCardStackView()

            binding.progressBar.visibility = View.GONE
            setViewsVisibility(View.VISIBLE)

            if (it.isEmpty()) {
                setupViewsVisibilityOnLastCardSwiped()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        setViewsVisibility(View.GONE)
    }

    private fun setViewsVisibility(visibility: Int) {
        binding.playButton.visibility = visibility
        binding.likeButton.visibility = visibility
        binding.dislikeButton.visibility = visibility
    }

    private fun setupCardStackLayoutManager() {
        cardStackLayoutManager = CardStackLayoutManager(
            requireContext(),
            CardStackListener(
                onMovieCardAppeared = { onCardAppeared(it) },
                onMovieCardSwiped = { onCardSwiped(it) }
            )
        )
    }

    private fun setupCardStackView() {
        cardStackLayoutManager.setMaxDegree(-20.0f)
        binding.cardStackView.layoutManager = cardStackLayoutManager
        binding.cardStackView.adapter = cardStackAdapter
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
        binding.movieNameTextView.text = cardStackAdapter.currentList[position].name
    }

    private fun onCardDisappeared(position: Int) {
        if (position == moviesList.size) {
            setupViewsVisibilityOnLastCardSwiped()
        }
        if (lastCardDirection == Direction.Left) {
            viewModel.deleteMovieFromCompilation(moviesList[position - 1].id) { handleErrors(it) }
        }
    }

    private fun handleErrors(errorModel: ErrorModel) {
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
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