package com.ithirteeng.features.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.customextensions.presentation.setEditTextInputSpaceFilter
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.FragmentChangeCollectionBinding
import com.ithirteeng.features.collections.presentation.COLLECTION_CHANGE_VIEW_MODEL
import com.ithirteeng.features.collections.presentation.ChangeCollectionFragmentViewModel
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity
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

        return binding.root
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

    private fun setupViews() {
        binding.nameEditText.setText(localCollectionEntity.collectionName)
        binding.collectionImageView.setImageResource(localCollectionEntity.collectionImageId)
    }

}