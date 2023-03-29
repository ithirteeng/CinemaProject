package com.ithirteeng.features.movieinfo.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.movieinfo.R
import com.ithirteeng.features.movieinfo.databinding.FragmentMovieBinding
import com.ithirteeng.features.movieinfo.presentation.MovieFragmentViewModel
import com.ithirteeng.features.movieinfo.ui.adapter.CadresAdapter
import com.ithirteeng.features.movieinfo.ui.adapter.EpisodesAdapter
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"
        private const val MOVIE_TYPE = "MOVIE_TYPE"

        fun provideMovieScreen(movieId: String, moviesListType: MoviesListType) = FragmentScreen {
            MovieFragment().apply {
                val bundle = Bundle()
                bundle.putString(MOVIE_ID, movieId)
                bundle.putSerializable(MOVIE_TYPE, moviesListType)
                arguments = bundle
            }
        }
    }

    private val viewModel: MovieFragmentViewModel by viewModel()

    private lateinit var binding: FragmentMovieBinding

    private lateinit var movieId: String

    private lateinit var moviesListType: MoviesListType

    private val cadresAdapter by lazy {
        CadresAdapter()
    }

    private val episodesAdapter by lazy {
        EpisodesAdapter {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
        }
    }

    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_movie, container, false)
        binding = FragmentMovieBinding.bind(layout)

        movieId = arguments?.getString(MOVIE_ID, "").toString()
        moviesListType = arguments?.getSerializable(MOVIE_TYPE) as MoviesListType

        onGettingMovie()

        binding.episodesRecyclerView.adapter = episodesAdapter
        binding.cadresRecyclerView.adapter = cadresAdapter

        return binding.root
    }

    private fun onGettingMovie() {
        viewModel.makeGetMoviesListRequest(movieId, moviesListType) {
            handleErrors(it)
        }
        viewModel.getMovieLiveData().observe(this.viewLifecycleOwner) {
            onGettingEpisodesList()

            cadresAdapter.submitList(it?.imageUrls)
            setupScreenData(it)
        }

    }

    private fun onGettingEpisodesList() {
        viewModel.makeGetMovieEpisodesListRequest(movieId) {
            handleErrors(it)
        }
        viewModel.getMovieEpisodesLiveData().observe(this.viewLifecycleOwner) {
            episodesAdapter.submitList(it)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupScreenData(movieEntity: MovieEntity?) {
        binding.descriptionTextView.text = movieEntity?.description
        binding.ageTextView.text = movieEntity?.age
        Glide
            .with(binding.root)
            .load(movieEntity?.poster)
            .placeholder(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
            .error(binding.root.context.getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
            .into(binding.mainPosterImageView)
    }

    private fun handleErrors(errorModel: ErrorModel) {
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
    }

}