package com.ithirteeng.features.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.FragmentCollectionInfoBinding

class CollectionInfoFragment : Fragment() {

    companion object {
        private const val COLLECTION_INFO_KEY = "COLLECTION_INFO_KEY"
        private const val COLLECTION_NAME = "COLLECTION_NAME"

        fun provideCollectionInfoScreen(collectionId: String, collectionName: String) =
            FragmentScreen {
                CollectionInfoFragment().apply {
                    val bundle = Bundle()
                    bundle.putString(COLLECTION_INFO_KEY, collectionId)
                    bundle.putString(COLLECTION_NAME, collectionName)
                    arguments = bundle
                }
            }
    }

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