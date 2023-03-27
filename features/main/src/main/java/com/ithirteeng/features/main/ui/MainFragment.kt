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
import com.ithirteeng.features.main.presentation.MainFragmentViewModel
import com.ithirteeng.features.main.ui.adapter.InTrendMoviesAdapter
import com.ithirteeng.features.main.ui.adapter.NewMoviesAdapter
import com.ithirteeng.features.main.ui.adapter.RecentViewedMoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        val provideMainScreen = FragmentScreen { MainFragment() }
    }

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainFragmentViewModel by viewModel()

    private var finishedRequests = 0

    private val inTrendAdapter by lazy {
        InTrendMoviesAdapter {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
            // TODO: navigateToMovieInfo
        }
    }

    private val recentViewedAdapter by lazy {
        RecentViewedMoviesAdapter {
            Toast.makeText(requireContext(), "play ${it.name}", Toast.LENGTH_SHORT).show()
            // TODO: navigateToMovieInfo
        }
    }

    private val newMoviesAdapter by lazy {
        NewMoviesAdapter {
            Toast.makeText(requireContext(), "play ${it.name}", Toast.LENGTH_SHORT).show()
            // TODO: navigateToMovieInfo
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_main, container, false)
        binding = FragmentMainBinding.bind(layout)
        finishedRequests = 0

        onGettingInTrendMoviesList()
        onGettingRecentViewedMoviesList()
        onGettingNewMoviesList()

        binding.inTrendRecyclerView.adapter = inTrendAdapter
        binding.recentRecyclerView.adapter = recentViewedAdapter
        binding.newRecyclerView.adapter = newMoviesAdapter

        return binding.root
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
        if (finishedRequests == 3) {
            binding.progressBar.visibility = View.GONE
        }
    }


    private fun handleErrors(errorModel: ErrorModel) {
        ErrorHandler.showErrorDialog(requireContext(), parentFragmentManager, errorModel)
    }
}