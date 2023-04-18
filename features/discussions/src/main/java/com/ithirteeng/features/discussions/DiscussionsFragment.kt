package com.ithirteeng.features.discussions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ithirteeng.features.discussions.databinding.FragmentDiscussionsBinding

class DiscussionsFragment : Fragment() {

    private lateinit var binding: FragmentDiscussionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_discussions, container, false)
        binding = FragmentDiscussionsBinding.bind(layout)
        return binding.root
    }
}