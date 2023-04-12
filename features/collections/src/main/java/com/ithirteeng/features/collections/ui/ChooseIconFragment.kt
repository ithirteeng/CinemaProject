package com.ithirteeng.features.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.FragmentChooseIconBinding
import com.ithirteeng.features.collections.presentation.CHOOSE_ICON_VIEW_MODEL
import com.ithirteeng.features.collections.presentation.ChooseIconFragmentViewModel
import com.ithirteeng.features.collections.presentation.utils.ChooseIconReason
import com.ithirteeng.features.collections.ui.adapter.ChooseIconAdapter
import com.ithirteeng.shared.collections.presentation.collectionsIconsIds
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


class ChooseIconFragment : Fragment() {

    companion object {

        private const val CREATION_REASON = "CREATION_REASON"

        fun provideChooseIconScreen(chooseIconReason: ChooseIconReason) = FragmentScreen {
            ChooseIconFragment().apply {
                val bundle = Bundle()
                bundle.putString(CREATION_REASON, chooseIconReason.string)
                arguments = bundle
            }
        }
    }

    private lateinit var binding: FragmentChooseIconBinding

    private val viewModel: ChooseIconFragmentViewModel by viewModel(named(CHOOSE_ICON_VIEW_MODEL))

    private lateinit var creationReason: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_choose_icon, container, false)
        binding = FragmentChooseIconBinding.bind(layout)

        creationReason = arguments?.getString(CREATION_REASON, "").toString()

        setupRecyclerView()
        onBackButtonClick()
        return binding.root
    }

    private fun onBackButtonClick() {
        binding.backButton.setOnClickListener {
            viewModel.exit(-1)
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = ChooseIconAdapter {
            if (creationReason == ChooseIconReason.CREATE.string) {
                viewModel.exit(it)
            } else {
                viewModel.exit(it)
            }
        }
        adapter.submitList(collectionsIconsIds)
        binding.recyclerView.adapter = adapter
    }

}