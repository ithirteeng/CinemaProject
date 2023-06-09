package com.ithirteeng.features.episode.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.*
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.customextensions.presentation.addBackPressedListener
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.episode.R
import com.ithirteeng.features.episode.databinding.FragmentEpisodeBinding
import com.ithirteeng.features.episode.presentation.EpisodeFragmentViewModel
import com.ithirteeng.shared.movies.entity.EpisodeEntity
import com.ithirteeng.shared.movies.entity.MovieEntity
import com.ithirteeng.shared.movies.utils.MoviesListType
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : Fragment() {

    companion object {
        private const val EPISODE_ID = "EPISODE_ID"
        private const val MOVIE_ID = "MOVIE_ID_EPISODE"
        private const val MOVIE_NAME = "MOVIE_NAME"
        private const val MOVIE_FILTER = "MOVIE_FILTER"
        private const val REQUEST_TO_FINISH_LOADING = 5

        fun provideEpisodeScreen(
            episodeId: String,
            movieId: String,
            movieName: String,
            moviesListType: MoviesListType,
        ) = FragmentScreen {
            EpisodeFragment().apply {
                val bundle = Bundle()
                bundle.putString(EPISODE_ID, episodeId)
                bundle.putString(MOVIE_ID, movieId)
                bundle.putString(MOVIE_NAME, movieName)
                bundle.putString(MOVIE_FILTER, moviesListType.type)
                arguments = bundle
            }
        }
    }

    private lateinit var binding: FragmentEpisodeBinding

    private val viewModel: EpisodeFragmentViewModel by viewModel()

    private lateinit var episodeId: String

    private lateinit var movieId: String

    private lateinit var movieName: String

    private lateinit var movieFilter: String

    private lateinit var movieInfo: MovieEntity

    private var videoTime: Int = 0

    private var episodeEntity: EpisodeEntity? = null

    private var finishedRequests = 0

    private val exoPlayer by lazy { ExoPlayer.Builder(requireContext()).build() }

    private val collectionsAdapter by lazy {
        CollectionsAdapter { entity ->
            viewModel.addMovieToCollection(movieId, entity.id) { handleErrors(it) }
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getAddToCollectionResultLiveData().observe(this.viewLifecycleOwner) {
                binding.collectionRecyclerView.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_episode, container, false)
        binding = FragmentEpisodeBinding.bind(layout)

        binding.progressBar.visibility = View.VISIBLE
        setAllViewsVisibility(View.INVISIBLE)

        finishedRequests = 0
        getBundleValues()
        setupOnGettingFunctions()

        setupCollectionsRecyclerView()
        setupOnClickButtonFunctions()
        return binding.root
    }

    private fun setupOnClickButtonFunctions() {
        onBackButtonClick()
        onBackArrowClick()
        onAddButtonClick()
        onLikeButtonClick()
        onChatButtonClick()
    }

    private fun setupCollectionsRecyclerView() {
        binding.collectionRecyclerView.adapter = collectionsAdapter
        viewModel.getCollectionsList { handleErrors(it) }
        viewModel.getCollectionsListLiveData().observe(this.viewLifecycleOwner) {
            collectionsAdapter.submitList(it)
        }
    }

    private fun getBundleValues() {
        episodeId = arguments?.getString(EPISODE_ID, "").toString()
        movieId = arguments?.getString(MOVIE_ID, "").toString()
        movieName = arguments?.getString(MOVIE_NAME, "").toString()
        movieFilter = arguments?.getString(MOVIE_FILTER, "").toString()
    }


    private fun onAddButtonClick() {
        binding.addButton.setOnClickListener {
            if (binding.collectionRecyclerView.visibility == View.VISIBLE) {
                binding.collectionRecyclerView.visibility = View.GONE
            } else {
                binding.collectionRecyclerView.visibility = View.VISIBLE
            }

        }
    }

    private fun onLikeButtonClick() {
        binding.heartButton.setOnClickListener {
            val favouritesCollection = viewModel.findFavouritesCollection()
            binding.progressBar.visibility = View.VISIBLE
            viewModel.addMovieToCollection(movieId, favouritesCollection?.id.toString()) {
                handleErrors(it)
            }
            viewModel.getAddToCollectionResultLiveData().observe(this.viewLifecycleOwner) {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun onChatButtonClick() {
        binding.chatButton.setOnClickListener {
            onExit {
                viewModel.navigateToChatScreen(
                    movieInfo.chatInfo.chatId,
                    movieInfo.chatInfo.chatName
                )
            }
        }
    }


    private fun onBackButtonClick() {
        binding.backButton.setOnClickListener {
            onExit { viewModel.exit() }
        }
    }

    private fun onBackArrowClick() {
        this.addBackPressedListener {
            onExit { viewModel.exit() }
        }
    }


    private fun onExit(doOnExit: () -> Unit) {
        exoPlayer.stop()
        viewModel.setEpisodeTime(episodeId, (exoPlayer.contentPosition / 1000).toInt()) {
            handleErrors(it)
        }
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getSuccessfulRequestLiveData().observe(this.viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            doOnExit()
        }
    }

    private fun setupOnGettingFunctions() {
        onGettingEpisodesList()
        onGettingMovieYears()
        onGettingEpisodeData()
        onGettingMovieInfo()
    }

    private fun onGettingMovieInfo() {
        viewModel.makeGetMovieInfoRequest(movieId, movieFilter) { handleErrors(it) }
        viewModel.getMovieInfoLiveData().observe(this.viewLifecycleOwner) {
            movieInfo = it
            setupMainPoster(it.poster)

            finishedRequests++
            handleViewsVisibility()
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
            setupVideoPlayer(it)

            finishedRequests++
            handleViewsVisibility()
        }
    }

    private fun setupVideoPlayer(timeInSeconds: Int) {
        with(binding) {
            videoPlayer.player = exoPlayer
            val mediaItem = MediaItem.fromUri(Uri.parse(episodeEntity?.filePath))
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.seekTo((timeInSeconds * 1000).toLong())
            setupPlayerListener()
            exoPlayer.play()

        }
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
        if (finishedRequests == REQUEST_TO_FINISH_LOADING) {
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
        binding.progressBar.visibility = View.GONE
        binding.collectionRecyclerView.visibility = View.GONE
    }

    private fun setupPlayerListener() {
        val volumeButton = binding.videoPlayer.findViewById<ImageButton>(R.id.volumeButton)
        volumeButton.setOnClickListener {
            // Toast.makeText(requireContext(), " DSFd", Toast.LENGTH_SHORT).show()
        }

    }

}