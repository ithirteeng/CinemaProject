package com.ithirteeng.features.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.main.R
import com.ithirteeng.features.main.databinding.FragmentMainBinding
import com.ithirteeng.features.main.domain.utils.MoviesListType
import com.ithirteeng.features.main.presentation.MainFragmentViewModel
import com.ithirteeng.features.main.ui.adapter.InTrendMoviesAdapter
import com.ithirteeng.features.main.ui.adapter.RecentViewedMoviesAdapter
import com.ithirteeng.shared.movies.entity.MovieEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        val provideMainScreen = FragmentScreen { MainFragment() }
    }

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainFragmentViewModel by viewModel()

    private val inTrendAdapter by lazy {
        InTrendMoviesAdapter {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
            // TODO: navigateToMovieInfo
        }
    }

    private val recentViewedAdapter by lazy {
        RecentViewedMoviesAdapter {
            Toast.makeText(requireContext(), "play ${it.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_main, container, false)
        binding = FragmentMainBinding.bind(layout)

        onGettingInTrendMoviesList()
        onGettingRecentViewedMoviesList()

        binding.inTrendRecyclerView.adapter = inTrendAdapter

        return binding.root
    }

    private fun onGettingInTrendMoviesList() {
        onGettingMoviesList(MoviesListType.IN_TREND) {
            inTrendAdapter.submitList(it)
            if (it.isNotEmpty()) {
                binding.inTrendRecyclerView.visibility = View.VISIBLE
                binding.inTrendTextView.visibility = View.VISIBLE
            }
        }
    }

    private fun onGettingRecentViewedMoviesList() {
        onGettingMoviesList(MoviesListType.LAST_VIEW) {
            recentViewedAdapter.submitList(it)
            if (it.isNotEmpty()) {
                binding.recentTextView.visibility = View.VISIBLE
                binding.recentRecyclerView.visibility = View.VISIBLE
            }
        }

    }

    private fun onGettingMoviesList(
        moviesListType: MoviesListType,
        onGettingData: (moviesList: List<MovieEntity>) -> Unit,
    ) {
        viewModel.makeGetMoviesListRequest(moviesListType) { handleErrors(it) }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getMoviesLiveData().observe(this.viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            onGettingData(it)
        }
    }

    private fun handleErrors(errorModel: ErrorModel) {
        ErrorHandler.showErrorDialog(requireContext(), parentFragmentManager, errorModel)
    }
}