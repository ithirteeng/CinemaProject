package com.ithirteeng.features.episode.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.episode.R
import com.ithirteeng.features.episode.databinding.FragmentEpisodeBinding

class EpisodeFragment : Fragment() {

    companion object {
        private const val EPISODE_ID = "EPISODE_ID"
        private const val MOVIE_ID = "MOVIE_ID_EPISODE"

        fun provideEpisodeScreen(episodeId: String, movieId: String) = FragmentScreen {
            EpisodeFragment().apply {
                val bundle = Bundle()
                bundle.putString(EPISODE_ID, episodeId)
                bundle.putString(MOVIE_ID, movieId)
                arguments = bundle
            }
        }
    }

    private lateinit var binding: FragmentEpisodeBinding

    private lateinit var episodeId: String

    private lateinit var movieId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_episode, container, false)
        binding = FragmentEpisodeBinding.bind(layout)

        episodeId = arguments?.getString(EPISODE_ID, "").toString()
        movieId = arguments?.getString(MOVIE_ID, "").toString()

        return binding.root
    }

}