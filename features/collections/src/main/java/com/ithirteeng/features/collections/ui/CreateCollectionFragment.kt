package com.ithirteeng.features.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.FragmentCreateCollectionBinding

class CreateCollectionFragment : Fragment() {

    companion object {
        val provideCreateCollectionScreen = FragmentScreen { CreateCollectionFragment() }
    }

    private lateinit var binding: FragmentCreateCollectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_create_collection, container, false)
        binding = FragmentCreateCollectionBinding.bind(layout)
        return binding.root
    }

}