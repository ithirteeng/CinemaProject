package com.ithirteeng.features.entry.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.entry.login.R
import com.ithirteeng.features.entry.login.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    companion object {
        val provideLoginScreen = FragmentScreen { LoginFragment() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.bind(layout)

        return binding.root
    }

}