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
import com.ithirteeng.features.collections.databinding.FragmentCollectionsBinding
import com.ithirteeng.features.collections.presentation.CollectionsFragmentViewModel
import com.ithirteeng.features.collections.ui.adapter.CollectionsAdapter
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

        binding.progressBar.visibility = View.VISIBLE
        binding.collectionsRecyclerView.adapter = collectionsAdapter
        onGettingCollectionsList()

        return binding.root
    }

    private fun onGettingCollectionsList() {
        viewModel.getCollectionsList { handleErrors(it) }
        viewModel.getCollectionsListLiveData().observe(this.viewLifecycleOwner) {
            collectionsAdapter.submitList(it)
            binding.progressBar.visibility = View.GONE
        }

    }

    private fun handleErrors(errorModel: ErrorModel) {
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
    }

}