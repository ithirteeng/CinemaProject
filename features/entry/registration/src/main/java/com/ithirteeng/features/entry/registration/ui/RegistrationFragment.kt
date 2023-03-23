package com.ithirteeng.features.entry.registration.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.entry.registration.R
import com.ithirteeng.features.entry.registration.databinding.FragmentRegistrationBinding
import com.ithirteeng.features.entry.registration.presentation.RegistrationFragmentViewModel
import com.ithirteeng.shared.validators.common.ValidationResult
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
            if (validateFields()) {
//                viewModel.postRegistrationData(
//                    RegistrationEntity(
//                        email = binding.emailEditText.text.toString(),
//                        firstName = binding.nameEditText.text.toString(),
//                        lastName = binding.surnameEditText.text.toString(),
//                        password = binding.passwordEditText.text.toString()
//                    ),
//                    onErrorAppearance = { handleErrors(it) }
//                )

                onSuccessfulSendingRequest()
            }
            // TODO: validate and post data + disable buttons
            hideKeyboard()
        }
    }

    private fun onSuccessfulSendingRequest() {
        viewModel.getRequestLiveData().observe(this.viewLifecycleOwner) {
            binding.loginButton.isEnabled = true
            binding.registrationButton.isEnabled = true
        }
    }

    private fun validateFields(): Boolean {
        val validationResults = listOf(
            viewModel.validateEmail(binding.emailEditText.text.toString()),
            viewModel.validateTextField(binding.nameEditText.text.toString()),
            viewModel.validateTextField(binding.surnameEditText.text.toString()),
            viewModel.validatePasswords(
                binding.passwordEditText.text.toString(),
                binding.repeatPasswordEditText.text.toString()
            )
        )
        for (validationResult in validationResults) {
            if (validationResult != ValidationResult.OK) {
                onErrorValidationResult(validationResult)
                return false
            }
        }
        return true
    }

    private fun onErrorValidationResult(validationResult: ValidationResult) {
        handleErrors(ErrorModel(422, getString(validationResult.errorStringId)))
        binding.registrationButton.isEnabled = true
        binding.loginButton.isEnabled = true
    }

    private fun handleErrors(errorModel: ErrorModel) {
        ErrorHandler.showErrorDialog(requireContext(), parentFragmentManager, errorModel)
        binding.registrationButton.isEnabled = true
        binding.loginButton.isEnabled = true
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity?.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}