package com.ithirteeng.features.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.component.design.R.string.empty_error
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.FragmentCreateCollectionBinding
import com.ithirteeng.features.collections.presentation.COLLECTION_CREATE_VIEW_MODEL
import com.ithirteeng.features.collections.presentation.CreateCollectionFragmentViewModel
import com.ithirteeng.shared.collections.presentation.collectionsIconsIds
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class CreateCollectionFragment : Fragment() {

    companion object {
        fun provideCreateCollectionScreen() = FragmentScreen { CreateCollectionFragment() }

    }

    private lateinit var binding: FragmentCreateCollectionBinding

    private val viewModel: CreateCollectionFragmentViewModel by viewModel(
        named(COLLECTION_CREATE_VIEW_MODEL)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_create_collection, container, false)
        binding = FragmentCreateCollectionBinding.bind(layout)

        onChooseIconButtonClick()
        onSaveButtonClick()
        onBackButtonClick()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val imageId = viewModel.getChosenIconId()
        if (imageId != -1) {
            binding.collectionImageView.setImageResource(imageId)
        } else {
            binding.collectionImageView.setImageResource(collectionsIconsIds[0])
        }
    }

    private fun onBackButtonClick() {
        binding.backButton.setOnClickListener {
            viewModel.exit()
        }
    }

    private fun onChooseIconButtonClick() {
        binding.chooseIconButton.setOnClickListener {
            viewModel.navigateToChooseIconScreen()
        }
    }

    private fun onSaveButtonClick() {
        binding.saveButton.setOnClickListener {
            if (checkCollectionNameValidity()) {
                viewModel.createCollection(binding.nameEditText.text.toString()) { handleErrors(it) }
                viewModel.getCreateCollectionResultLiveData().observe(this.viewLifecycleOwner) {
                    viewModel.exit()
                }
            } else {
                Toast.makeText(requireContext(), getString(empty_error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkCollectionNameValidity(): Boolean {
        return binding.nameEditText.text.isNotEmpty()
    }

    private fun handleErrors(errorModel: ErrorModel) {
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
    }

}