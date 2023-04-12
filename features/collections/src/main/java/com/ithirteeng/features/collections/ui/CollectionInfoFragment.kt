package com.ithirteeng.features.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.FragmentCollectionInfoBinding
import com.ithirteeng.features.collections.presentation.COLLECTION_INFO_VIEW_MODEL
import com.ithirteeng.features.collections.presentation.CollectionInfoFragmentViewModel
import com.ithirteeng.features.collections.ui.adapter.CollectionMoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class CollectionInfoFragment : Fragment() {

    companion object {
        private const val COLLECTION_INFO_KEY = "COLLECTION_INFO_KEY"
        private const val COLLECTION_NAME = "COLLECTION_NAME"

        fun provideCollectionInfoScreen(collectionId: String, collectionName: String) =
            FragmentScreen {
                CollectionInfoFragment().apply {
                    val bundle = Bundle()
                    bundle.putString(COLLECTION_INFO_KEY, collectionId)
                    bundle.putString(COLLECTION_NAME, collectionName)
                    arguments = bundle
                }
            }
    }

    private lateinit var binding: FragmentCollectionInfoBinding

    private val viewModel: CollectionInfoFragmentViewModel by viewModel(named(COLLECTION_INFO_VIEW_MODEL))

    private lateinit var collectionId: String

    private lateinit var collectionName: String

    private val moviesAdapter by lazy {
        CollectionMoviesAdapter {
            viewModel.navigateToMovieInfoScreen(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_collection_info, container, false)
        binding = FragmentCollectionInfoBinding.bind(layout)

        binding.progressBar.visibility = View.VISIBLE
        collectionId = arguments?.getString(COLLECTION_INFO_KEY).toString()
        collectionName = arguments?.getString(COLLECTION_NAME).toString()
        binding.fragmentNameTextView.text = collectionName

        binding.moviesRecyclerView.adapter = moviesAdapter

        onGettingMoviesList()

        onBackButtonPressed()

        return binding.root
    }

    private fun onBackButtonPressed() {
        binding.backButton.setOnClickListener {
            viewModel.exit()
        }
    }

    private fun onGettingMoviesList() {
        viewModel.makeGetMoviesListRequest(collectionId) { handleErrors(it) }
        viewModel.getMovieListLiveData().observe(this.viewLifecycleOwner) {
            moviesAdapter.submitList(it)
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun handleErrors(errorModel: ErrorModel) {
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
    }

}