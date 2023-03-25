package com.ithirteeng.features.entry.login.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.ithirteeng.errorhandler.domain.ErrorModel
import com.ithirteeng.errorhandler.presentation.ErrorHandler
import com.ithirteeng.features.entry.login.R
import com.ithirteeng.features.entry.login.databinding.FragmentLoginBinding
import com.ithirteeng.features.entry.login.domain.entity.LoginEntity
import com.ithirteeng.features.entry.login.presentation.LoginFragmentViewModel
import com.ithirteeng.shared.validators.common.ValidationResult
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginFragmentViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.bind(layout)
        setupOnButtonClickFunctions()

        return binding.root
    }

    private fun setupOnButtonClickFunctions() {
        onRegistrationButtonCLick()
        onLoginButtonClick()
    }

    private fun onLoginButtonClick() {
        binding.loginButton.setOnClickListener {
            hideKeyboard()
            binding.registrationButton.isEnabled = false
            binding.loginButton.isEnabled = false
            if (validateFields()) {
                binding.progressBar.visibility = View.VISIBLE
                viewModel.postLoginData(
                    LoginEntity(
                        binding.emailEditText.text.toString(),
                        binding.passwordEditText.text.toString()
                    ),
                    onErrorAppearance = { handleErrors(it) }
                )
                onGettingTokenEntity()
            }
        }
    }

    private fun onGettingTokenEntity() {
        viewModel.getTokenLiveData().observe(this.viewLifecycleOwner) {
            viewModel.navigateToMainHostScreen()
            binding.loginButton.isEnabled = true
            binding.registrationButton.isEnabled = true
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun onRegistrationButtonCLick() {
        binding.registrationButton.setOnClickListener {
            viewModel.navigateToRegistrationScreen()
        }
    }

    private fun validateFields(): Boolean {
        val validationResults = listOf(
            viewModel.validateEmail(binding.emailEditText.text.toString()),
            viewModel.validateTextField(binding.passwordEditText.text.toString())
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
        ErrorHandler.showErrorDialog(requireContext(), parentFragmentManager, errorModel)
        binding.progressBar.visibility = View.GONE
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