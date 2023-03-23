package com.ithirteeng.features.entry.registration.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.entry.registration.R
import com.ithirteeng.features.entry.registration.databinding.FragmentRegistrationBinding
import com.ithirteeng.features.entry.registration.presentation.RegistrationFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    companion object {
        val provideRegistrationScreen = FragmentScreen { RegistrationFragment() }
    }

    private val viewModel: RegistrationFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.fragment_registration, container, false)
        binding = FragmentRegistrationBinding.bind(layout)
        setupOnClickButtonsFunctions()

        return binding.root
    }

    private fun setupOnClickButtonsFunctions() {
        onLoginButtonCLick()
        onRegistrationButtonClick()
    }

    private fun onLoginButtonCLick() {
        binding.loginButton.setOnClickListener {
            viewModel.navigateToLoginScreen()
        }
    }

    private fun onRegistrationButtonClick() {
        binding.registrationButton.setOnClickListener {

            // TODO: validate and post data + disable buttons
            onSuccessfulSendingRequest()
        }
    }

    private fun onSuccessfulSendingRequest() {
        viewModel.getRequestLiveData().observe(this.viewLifecycleOwner) {
            binding.loginButton.isEnabled = true
            binding.registrationButton.isEnabled = true
        }
    }

    private fun handleErrors(errorModel: ErrorModel) {
        ErrorHandler.showErrorDialog(requireContext(), parentFragmentManager, errorModel)
        binding.registrationButton.isEnabled = true
        binding.loginButton.isEnabled = true
    }
}