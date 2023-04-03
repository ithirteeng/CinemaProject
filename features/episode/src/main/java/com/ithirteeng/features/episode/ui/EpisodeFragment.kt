package com.ithirteeng.features.episode.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.*
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.episode.R
import com.ithirteeng.features.episode.databinding.FragmentEpisodeBinding
import com.ithirteeng.features.episode.presentation.EpisodeFragmentViewModel
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : Fragment() {

    companion object {
        private const val EPISODE_ID = "EPISODE_ID"
        private const val MOVIE_ID = "MOVIE_ID_EPISODE"
        private const val MOVIE_NAME = "MOVIE_NAME"

        fun provideEpisodeScreen(episodeId: String, movieId: String, movieName: String) =
            FragmentScreen {
                EpisodeFragment().apply {
                    val bundle = Bundle()
                    bundle.putString(EPISODE_ID, episodeId)
                    bundle.putString(MOVIE_ID, movieId)
                    bundle.putString(MOVIE_NAME, movieName)
                    arguments = bundle
                }
            }
    }

    private lateinit var binding: FragmentEpisodeBinding

    private val viewModel: EpisodeFragmentViewModel by viewModel()

    private lateinit var episodeId: String

    private lateinit var movieId: String

    private lateinit var movieName: String

    private var videoTime: Int = 0

    private var episodeEntity: EpisodeEntity? = null

    private var finishedRequests = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_episode, container, false)
        binding = FragmentEpisodeBinding.bind(layout)

        finishedRequests = 0
        binding.progressBar.visibility = View.VISIBLE
        setAllViewsVisibility(View.INVISIBLE)

        getBundleValues()

        setupOnGettingFunctions()

        onBackButtonClick()
        return binding.root
    }

    private fun getBundleValues() {
        episodeId = arguments?.getString(EPISODE_ID, "").toString()
        movieId = arguments?.getString(MOVIE_ID, "").toString()
        movieName = arguments?.getString(MOVIE_NAME, "").toString()
    }

    private fun setupOnGettingFunctions() {
        onGettingEpisodesList()
        onGettingMovieYears()
        onGettingEpisodeData()
    }

    private fun onBackButtonClick() {
        binding.backButton.setOnClickListener {
            viewModel.exit()
        }
    }

    private fun onGettingEpisodesList() {
        viewModel.makeGetEpisodesListUseCase(movieId) { handleErrors(it) }
        viewModel.getEpisodesListLiveData().observe(this.viewLifecycleOwner) {
            viewModel.getEpisodeData(episodeId, it)
            viewModel.setupMovieYears(it)

            finishedRequests++
            handleViewsVisibility()
        }
    }

    private fun onGettingMovieYears() {
        viewModel.getYearsLiveData().observe(this.viewLifecycleOwner) {
            if (it == null) {
                binding.movieYearsTextView.text = ""
            } else {
                binding.movieYearsTextView.text = it
            }

            finishedRequests++
            handleViewsVisibility()
        }
    }

    private fun onGettingEpisodeData() {
        viewModel.getEpisodeLiveData().observe(this.viewLifecycleOwner) {
            binding.movieNameTextView.text = movieName
            binding.movieHeaderTextView.text = it?.name
            setupMainPoster(it?.preview)
            binding.descriptionTextView.text = it?.description
            episodeEntity = it

            onGettingEpisodeTime()

            finishedRequests++
            handleViewsVisibility()
        }
    }

    private fun onGettingEpisodeTime() {
        viewModel.makeGetEpisodeTimeRequest(episodeId) { handleErrors(it) }
        viewModel.getEpisodeTimeLiveData().observe(this.viewLifecycleOwner) {
            videoTime = it
            setupVideoView()
        }
    }

    private fun setupVideoView() {
        binding.videoView.setMediaController(MediaController(requireContext()))
        binding.videoView.setVideoPath(episodeEntity?.filePath)
        binding.videoView.requestFocus(0)

        binding.videoView.start()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupMainPoster(url: String?) {
        Glide
            .with(binding.root)
            .load(url)
            .placeholder(requireContext().getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
            .error(requireContext().getDrawable(com.ithirteeng.component.design.R.drawable.image_placeholder))
            .into(binding.episodePosterImageView)
    }


    private fun handleViewsVisibility() {
        if (finishedRequests == 3) {
            setAllViewsVisibility(View.VISIBLE)
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setAllViewsVisibility(visibility: Int) {
        binding.allViewsGroup.visibility = visibility
    }

    private fun handleErrors(errorModel: ErrorModel) {
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
    }

}