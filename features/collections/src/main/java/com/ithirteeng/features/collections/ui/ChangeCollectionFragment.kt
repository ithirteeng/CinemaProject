package com.ithirteeng.features.collections.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.collections.R
import com.ithirteeng.features.collections.databinding.FragmentChangeCollectionBinding
import com.ithirteeng.shared.collections.domain.entity.LocalCollectionEntity

class ChangeCollectionFragment : Fragment() {

    companion object {
        private const val COLLECTION_KEY = "collection key"
        fun provideChangeCollectionScreen(localCollectionEntity: LocalCollectionEntity) =
            FragmentScreen {
                ChangeCollectionFragment().apply {
                    val bundle = Bundle()
                    bundle.putSerializable(COLLECTION_KEY, localCollectionEntity)
                }
            }
    }

    private lateinit var binding: FragmentChangeCollectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_change_collection, container, false)
        binding = FragmentChangeCollectionBinding.bind(layout)

        return binding.root
    }

}