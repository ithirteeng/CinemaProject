package com.ithirteeng.features.entry.registration.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.component.design.R.string.favourites_collection
import com.ithirteeng.customextensions.presentation.setEditTextInputSpaceFilter
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.entry.registration.R
import com.ithirteeng.features.entry.registration.databinding.FragmentRegistrationBinding
import com.ithirteeng.features.entry.registration.domain.entity.RegistrationEntity
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
        savedInstanceState: Bundle?,
    ): View {
        val layout = inflater.inflate(R.layout.fragment_registration, container, false)
        binding = FragmentRegistrationBinding.bind(layout)
        setupOnClickButtonsFunctions()
        setEditTextsInputTextFilter()

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
            binding.registrationButton.isEnabled = false
            binding.loginButton.isEnabled = false
            if (validateFields()) {
                binding.progressBar.visibility = View.VISIBLE
                viewModel.postRegistrationData(
                    RegistrationEntity(
                        email = binding.emailEditText.text.toString(),
                        firstName = binding.nameEditText.text.toString(),
                        lastName = binding.surnameEditText.text.toString(),
                        password = binding.passwordEditText.text.toString()
                    ),
                    onErrorAppearance = { handleErrors(it) }
                )

                onSuccessfulSendingRequest()
            }
            hideKeyboard()
        }
    }

    private fun onSuccessfulSendingRequest() {
        viewModel.getRequestLiveData().observe(this.viewLifecycleOwner) {
            onCreatingFavouritesCollection()
        }
    }

    private fun onCreatingFavouritesCollection() {
        viewModel.createFavouritesCollection(getString(favourites_collection)) { handleErrors(it) }
        viewModel.getCollectionLiveData().observe(this.viewLifecycleOwner) {
            viewModel.navigateToMainHostScreen()
            binding.loginButton.isEnabled = true
            binding.registrationButton.isEnabled = true
            binding.progressBar.visibility = View.GONE
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
        binding.progressBar.visibility = View.GONE
    }

    private fun handleErrors(errorModel: ErrorModel) {
        childFragmentManager.executePendingTransactions()
        ErrorHandler.showErrorDialog(requireContext(), childFragmentManager, errorModel)
        binding.registrationButton.isEnabled = true
        binding.loginButton.isEnabled = true
        binding.progressBar.visibility = View.GONE
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

    private fun setEditTextsInputTextFilter() {
        binding.emailEditText.setEditTextInputSpaceFilter()
        binding.nameEditText.setEditTextInputSpaceFilter()
        binding.surnameEditText.setEditTextInputSpaceFilter()
        binding.repeatPasswordEditText.setEditTextInputSpaceFilter()
        binding.passwordEditText.setEditTextInputSpaceFilter()
    }
}