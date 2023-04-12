package com.ithirteeng.features.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.customextensions.presentation.setEditTextInputSpaceFilter
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.FragmentChangeCollectionBinding
import com.ithirteeng.features.collections.presentation.COLLECTION_CHANGE_VIEW_MODEL
import com.ithirteeng.features.collections.presentation.ChangeCollectionFragmentViewModel
import com.ithirteeng.shared.collections.domain.entity.CollectionEntity
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
import com.ithirteeng.shared.collections.presentation.collectionsIconsIds
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class ChangeCollectionFragment : Fragment() {

    companion object {
        private const val COLLECTION_CHANGE_KEY = "COLLECTION_CHANGE_KEY"

        fun provideChangeCollectionScreen(localCollectionEntity: LocalCollectionEntity) =
            FragmentScreen {
                ChangeCollectionFragment().apply {
                    val bundle = Bundle()
                    bundle.putSerializable(COLLECTION_CHANGE_KEY, localCollectionEntity)
                    arguments = bundle
                }
            }
    }

    private lateinit var localCollectionEntity: LocalCollectionEntity

    private var imageId = collectionsIconsIds[0]

    private lateinit var binding: FragmentChangeCollectionBinding

    private val viewModel: ChangeCollectionFragmentViewModel by viewModel(
        named(
            COLLECTION_CHANGE_VIEW_MODEL
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_change_collection, container, false)
        binding = FragmentChangeCollectionBinding.bind(layout)
        binding.nameEditText.setEditTextInputSpaceFilter()

        getCollectionInfo()
        setupViews()

        onBackButtonClick()
        onChooseIconButtonClick()
        onSaveButtonClick()
        onDeleteButtonClick()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val imageId = viewModel.getChosenIconId()
        if (imageId != -1) {
            this.imageId = imageId
            binding.collectionImageView.setImageResource(imageId)
        } else {
            binding.collectionImageView.setImageResource(localCollectionEntity.collectionImageId)
        }
    }

    @Suppress("DEPRECATION")
    private fun getCollectionInfo() {
        localCollectionEntity =
            arguments?.getSerializable(COLLECTION_CHANGE_KEY) as LocalCollectionEntity
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
                binding.progressBar.visibility = View.VISIBLE
                onGettingCollectionList()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(com.ithirteeng.component.design.R.string.collection_name_empty_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onDeleteButtonClick() {
        binding.deleteButton.setOnClickListener {
            viewModel.deleteCollection(localCollectionEntity) { handleErrors(it) }
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getOnDeletingCollectionLiveData().observe(this.viewLifecycleOwner) {
                binding.progressBar.visibility = View.GONE
                viewModel.exit()
            }
        }
    }

    private fun onGettingCollectionList() {
        viewModel.makeGetCollectionListRequest { handleErrors(it) }
        viewModel.getCollectionListLiveData().observe(this.viewLifecycleOwner) {
            if (viewModel.checkOnCorrectName(
                    localCollectionEntity.collectionName,
                    binding.nameEditText.text.toString(),
                    it
                )
            ) {
                onDeletingCollection()
            } else {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    getString(com.ithirteeng.component.design.R.string.collection_name_exists_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onDeletingCollection() {
        viewModel.deleteCollection(localCollectionEntity) { handleErrors(it) }
        viewModel.getOnDeletingCollectionLiveData().observe(this.viewLifecycleOwner) {
            onCreatingCollection()
        }
    }

    private fun onCreatingCollection() {
        viewModel.createCollection(binding.nameEditText.text.toString()) { handleErrors(it) }
        viewModel.getCreateCollectionResultLiveData().observe(this.viewLifecycleOwner) {
            onSavingCollectionLocally(it)
        }
    }

    private fun onSavingCollectionLocally(collectionEntity: CollectionEntity) {
        viewModel.saveCollectionLocally(collectionEntity, imageId)
        viewModel.getOnSavingCollectionLiveData().observe(this.viewLifecycleOwner) {
            viewModel.exit()
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun checkCollectionNameValidity(): Boolean {
        return binding.nameEditText.text.isNotEmpty()
    }

    private fun handleErrors(errorModel: ErrorModel) {
        binding.progressBar.visibility = View.GONE
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
    }


    private fun setupViews() {
        binding.nameEditText.setText(localCollectionEntity.collectionName)
        binding.collectionImageView.setImageResource(localCollectionEntity.collectionImageId)
    }

}