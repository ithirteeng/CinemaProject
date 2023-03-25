package com.ithirteeng.features.compilation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.compilation.databinding.FragmentCompilationBinding

class CompilationFragment : Fragment() {

    private lateinit var binding: FragmentCompilationBinding

    companion object {
        val provideCompilationScreen = FragmentScreen { CompilationFragment() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.fragment_compilation, container, false)
        binding = FragmentCompilationBinding.bind(layout)

        return binding.root
    }
}