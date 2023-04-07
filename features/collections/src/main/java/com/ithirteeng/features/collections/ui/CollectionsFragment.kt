package com.ithirteeng.features.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.component.design.R.string
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.FragmentCollectionsBinding
import com.ithirteeng.features.collections.presentation.CollectionsFragmentViewModel
import com.ithirteeng.features.collections.presentation.collectionsIconsIds
import com.ithirteeng.features.collections.ui.adapter.CollectionsAdapter
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CollectionsFragment : Fragment() {

    companion object {
        val provideCollectionsScreen = FragmentScreen { CollectionsFragment() }
    }

    private lateinit var binding: FragmentCollectionsBinding

    private val viewModel: CollectionsFragmentViewModel by viewModel()

    private val collectionsAdapter by lazy {
        CollectionsAdapter {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_collections, container, false)
        binding = FragmentCollectionsBinding.bind(layout)

        createFavouritesCollection()
        onGettingCollectionsList()
        binding.collectionsRecyclerView.adapter = collectionsAdapter

        return binding.root
    }

    private fun onGettingCollectionsList() {
        viewModel.getCollectionsList { handleErrors(it) }
        viewModel.getCollectionsListLiveData().observe(this.viewLifecycleOwner) {
            collectionsAdapter.submitList(it)
        }
    }

    private fun createFavouritesCollection() {
        if (!viewModel.getCreationFavouritesFlag()) {
            viewModel.createCollection(getString(string.favourites_collection)) { handleErrors(it) }
            viewModel.getCreateCollectionResultLiveData().observe(this.viewLifecycleOwner) {
                viewModel.saveCollectionLocally(
                    LocalCollectionEntity(
                        collectionId = it.id,
                        collectionName = it.name,
                        collectionImageId = collectionsIconsIds[0],
                        isFavourite = true
                    )
                )
                viewModel.setCreationFavouritesFlag(true)
            }
        }
    }


    private fun handleErrors(errorModel: ErrorModel) {
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
    }

}