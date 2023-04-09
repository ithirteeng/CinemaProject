package com.ithirteeng.features.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.FragmentCollectionInfoBinding

class CollectionInfoFragment : Fragment() {

    private lateinit var binding: FragmentCollectionInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_collection_info, container, false)
        binding = FragmentCollectionInfoBinding.bind(layout)

        return binding.root
    }

}