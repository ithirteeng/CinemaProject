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
import com.ithirteeng.features.collections.ui.adapter.ChooseIconAdapter
import com.ithirteeng.shared.collections.presentation.collectionsIconsIds


class ChooseIconFragment : Fragment() {

    companion object {
        val provideChooseIconScreen = FragmentScreen { ChooseIconFragment() }
    }

    private lateinit var binding: FragmentChooseIconBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_choose_icon, container, false)
        binding = FragmentChooseIconBinding.bind(layout)

        setupRecyclerView()
        onBackButtonClick()

        return binding.root
    }

    private fun onBackButtonClick() {
        binding.backButton.setOnClickListener {
            // todo navigate exit
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = StaggeredGridLayoutManager(4, LinearLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = ChooseIconAdapter {
            // todo: navigate to another screen
        }
        adapter.submitList(collectionsIconsIds)
        binding.recyclerView.adapter = adapter
    }

}