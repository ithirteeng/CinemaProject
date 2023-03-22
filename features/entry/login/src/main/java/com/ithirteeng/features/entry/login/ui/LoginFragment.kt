package com.ithirteeng.features.entry.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.ithirteeng.features.entry.login.R
import com.ithirteeng.features.entry.login.databinding.FragmentLoginBinding
import com.ithirteeng.features.entry.login.domain.entity.LoginEntity
import com.ithirteeng.features.entry.login.presentation.LoginFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    companion object {
        val provideLoginScreen = FragmentScreen { LoginFragment() }
    }

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

    private fun onGettingTokenEntity() {
        viewModel.getTokenLiveData().observe(this.viewLifecycleOwner) {
            viewModel.saveTokenToLocalStorage(it)
        }
    }

    private fun onRegistrationButtonCLick() {
        binding.registrationButton.setOnClickListener {
            viewModel.navigateToRegistrationScreen()
        }
    }

    private fun handleErrors(errorCode: Int) {
        Toast.makeText(requireContext(), errorCode.toString(), Toast.LENGTH_SHORT).show()
    }

}