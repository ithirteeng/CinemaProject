package com.ithirteeng.features.compilation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ithirteeng.features.compilation.databinding.FragmentCompilationBinding

class CompilationFragment : Fragment() {

    private lateinit var binding: FragmentCompilationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.fragment_compilation, container, false)
        binding = FragmentCompilationBinding.bind(layout)

        return binding.root
    }
}