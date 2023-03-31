package com.ithirteeng.features.main.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.main.R
import com.ithirteeng.features.main.databinding.FragmentMainBinding
import com.ithirteeng.features.main.domain.entity.PosterEntity
import com.ithirteeng.features.main.presentation.MainFragmentViewModel
import com.ithirteeng.features.main.ui.adapter.ForYouMoviesAdapter
import com.ithirteeng.features.main.ui.adapter.InTrendMoviesAdapter
import com.ithirteeng.features.main.ui.adapter.NewMoviesAdapter
import com.ithirteeng.features.main.ui.adapter.RecentViewedMoviesAdapter
import com.ithirteeng.shared.movies.utils.MoviesListType
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        val provideMainScreen = FragmentScreen { MainFragment() }
        private const val REQUEST_AMOUNT = 5
    }

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainFragmentViewModel by viewModel()

    private var finishedRequests = 0

    private val inTrendAdapter by lazy {
        InTrendMoviesAdapter {
            viewModel.navigateToMovieScreen(it.id, MoviesListType.IN_TREND)
        }
    }

    private val recentViewedAdapter by lazy {
        RecentViewedMoviesAdapter {
            viewModel.navigateToMovieScreen(it.id, MoviesListType.LAST_VIEW)
        }
    }

    private val newMoviesAdapter by lazy {
        NewMoviesAdapter {
            viewModel.navigateToMovieScreen(it.id, MoviesListType.NEW)
        }
    }

    private val forYouMoviesAdapter by lazy {
        ForYouMoviesAdapter {
            viewModel.navigateToMovieScreen(it.id, MoviesListType.FOR_ME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_main, container, false)
        binding = FragmentMainBinding.bind(layout)
        finishedRequests = 0

        setupButtonsVisibility(View.GONE)
        setupOnGettingDataFunctions()
        setupRecyclerViewAdapters()

        return binding.root
    }

    private fun setupRecyclerViewAdapters() {
        binding.inTrendRecyclerView.adapter = inTrendAdapter
        binding.recentRecyclerView.adapter = recentViewedAdapter
        binding.newRecyclerView.adapter = newMoviesAdapter
        binding.forYouRecyclerView.adapter = forYouMoviesAdapter
    }

    private fun setupOnGettingDataFunctions() {
        onGettingMainPoster()
        onGettingInTrendMoviesList()
        onGettingRecentViewedMoviesList()
        onGettingNewMoviesList()
        onGettingForYouMoviesList()
    }

    private fun onGettingMainPoster() {
        viewModel.makeGetPosterRequest { handleErrors(it) }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getPosterLiveData().observe(this.viewLifecycleOwner) {
            finishedRequests++
            setupMainPoster(it)
            handleProgressBarVisibility()
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupMainPoster(posterEntity: PosterEntity) {
        Glide
            .with(binding.root)
            .load(posterEntity.backgroundImage)
            .placeholder(requireContext().getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
            .error(requireContext().getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
            .into(binding.mainPosterImageView)
    }

    private fun onGettingInTrendMoviesList() {
        viewModel.makeGetInTrendMoviesListRequest { handleErrors(it) }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getInTrendMoviesLiveData().observe(this.viewLifecycleOwner) {

            finishedRequests++
            inTrendAdapter.submitList(it)
            handleProgressBarVisibility()

            if (it.isNotEmpty()) {
                binding.inTrendRecyclerView.visibility = View.VISIBLE
                binding.inTrendTextView.visibility = View.VISIBLE
            }
        }
    }

    private fun onGettingForYouMoviesList() {
        viewModel.makeGetForYouMoviesListRequest { handleErrors(it) }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getForYouMoviesLiveData().observe(this.viewLifecycleOwner) {

            finishedRequests++
            forYouMoviesAdapter.submitList(it)
            handleProgressBarVisibility()

            if (it.isNotEmpty()) {
                binding.forYouRecyclerView.visibility = View.VISIBLE
                binding.forYouTextView.visibility = View.VISIBLE
            }
        }
    }

    private fun onGettingRecentViewedMoviesList() {
        viewModel.makeGetRecentMoviesListRequest { handleErrors(it) }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getRecentMoviesLiveData().observe(this.viewLifecycleOwner) {

            finishedRequests++
            recentViewedAdapter.submitList(it)
            handleProgressBarVisibility()

            if (it.isNotEmpty()) {
                binding.recentTextView.visibility = View.VISIBLE
                binding.recentRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun onGettingNewMoviesList() {
        viewModel.makeGetNewMoviesListRequest { handleErrors(it) }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getNewMoviesLiveData().observe(this.viewLifecycleOwner) {
            finishedRequests++
            newMoviesAdapter.submitList(it)
            handleProgressBarVisibility()

            if (it.isNotEmpty()) {
                binding.newTextView.visibility = View.VISIBLE
                binding.newRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun handleProgressBarVisibility() {
        if (finishedRequests == REQUEST_AMOUNT) {
            binding.progressBar.visibility = View.GONE
            setupButtonsVisibility(View.VISIBLE)
        }
    }

    private fun setupButtonsVisibility(visibility: Int) {
        binding.watchButton.visibility = visibility
        binding.setInterestsButton.visibility = visibility
        binding.mainPosterImageView.visibility = visibility
    }

    private fun handleErrors(errorModel: ErrorModel) {
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
    }
}