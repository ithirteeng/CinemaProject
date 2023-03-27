package com.ithirteeng.features.splash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.splash.R
import com.ithirteeng.features.splash.databinding.FragmentSplashBinding
import com.ithirteeng.features.splash.presentation.SplashFragmentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    companion object {
        val provideSplashScreen = FragmentScreen { SplashFragment() }
    }

    private val viewModel: SplashFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.fragment_splash, container, false)
        binding = FragmentSplashBinding.bind(layout)

        lifecycleScope.launch {
            delay(500)
            navigateToCorrectScreen()
        }

        return binding.root
    }

    private fun navigateToCorrectScreen() {
        if (viewModel.checkIfUserEnteredTheApp()) {
            if (viewModel.checkTokenExistence()) {
                //viewModel.navigateToMainHostScreen()
                viewModel.navigateToLoginScreen()
            } else {
                viewModel.navigateToLoginScreen()
            }
        } else {
            viewModel.setUserEntryFlag(true)
            viewModel.navigateToRegistrationScreen()
        }
    }

}