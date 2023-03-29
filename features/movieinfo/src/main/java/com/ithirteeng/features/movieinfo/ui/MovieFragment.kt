package com.ithirteeng.features.movieinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.movieinfo.R
import com.ithirteeng.features.movieinfo.databinding.FragmentMovieBinding
import com.ithirteeng.features.movieinfo.presentation.MovieFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"

        fun provideMovieScreen(movieId: String) = FragmentScreen {
            MovieFragment().apply {
                val bundle = Bundle()
                bundle.putString(MOVIE_ID, movieId)
                arguments = bundle
            }
        }
    }

    private val viewModel: MovieFragmentViewModel by viewModel()

    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_movie, container, false)
        binding = FragmentMovieBinding.bind(layout)

        return binding.root
    }


}