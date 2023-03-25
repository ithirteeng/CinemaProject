package com.ithirteeng.features.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.collections.databinding.FragmentCollectionsBinding

class CollectionsFragment : Fragment() {

    private lateinit var binding: FragmentCollectionsBinding

    companion object {
        val provideCollectionsScreen = FragmentScreen { CollectionsFragment() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.fragment_collections, container, false)
        binding = FragmentCollectionsBinding.bind(layout)

        return binding.root
    }

}