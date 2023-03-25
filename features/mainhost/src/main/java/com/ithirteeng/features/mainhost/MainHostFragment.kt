package com.ithirteeng.features.mainhost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ithirteeng.features.mainhost.databinding.FragmentMainHostBinding

class MainHostFragment : Fragment() {

    private lateinit var binding: FragmentMainHostBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.fragment_main_host, container, false)
        binding = FragmentMainHostBinding.bind(layout)
        return binding.root
    }
}