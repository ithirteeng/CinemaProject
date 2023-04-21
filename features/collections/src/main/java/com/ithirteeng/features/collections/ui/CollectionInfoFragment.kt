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
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class CollectionInfoFragment : Fragment() {

    companion object {
        private const val COLLECTION_INFO_KEY = "COLLECTION_INFO_KEY"

        fun provideCollectionInfoScreen(localCollectionEntity: LocalCollectionEntity) =
            FragmentScreen {
                CollectionInfoFragment().apply {
                    val bundle = Bundle()
                    bundle.putSerializable(COLLECTION_INFO_KEY, localCollectionEntity)
                    arguments = bundle
                }
            }
    }

    private lateinit var binding: FragmentCollectionInfoBinding

    private val viewModel: CollectionInfoFragmentViewModel by viewModel(
        named(
            COLLECTION_INFO_VIEW_MODEL
        )
    )

    private lateinit var localCollectionEntity: LocalCollectionEntity

    private val moviesAdapter by lazy {
        CollectionMoviesAdapter {
            viewModel.navigateToMovieInfoScreen(it)
        }
    }

    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_collection_info, container, false)
        binding = FragmentCollectionInfoBinding.bind(layout)

        binding.progressBar.visibility = View.VISIBLE

        localCollectionEntity = arguments?.getSerializable(COLLECTION_INFO_KEY) as LocalCollectionEntity
        binding.fragmentNameTextView.text = localCollectionEntity.collectionName

        binding.moviesRecyclerView.adapter = moviesAdapter

        onGettingMoviesList()
        onRedactButtonClick()
        onBackButtonPressed()

        doIfCollectionIsFavourite()

        return binding.root
    }

    private fun doIfCollectionIsFavourite() {
        if (localCollectionEntity.isFavourite) {
            binding.redactionButton.visibility = View.GONE
        }
    }

    private fun onRedactButtonClick() {
        binding.redactionButton.setOnClickListener {
            viewModel.navigateToChangeCollectionScreen(localCollectionEntity)
        }
    }

    private fun onBackButtonPressed() {
        binding.backButton.setOnClickListener {
            viewModel.exit()
        }
    }

    private fun onGettingMoviesList() {
        viewModel.makeGetMoviesListRequest(localCollectionEntity.collectionId) { handleErrors(it) }
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