package com.alijan.laza.presentation.auth.signup

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.laza.common.utils.loadingDialog
import com.alijan.laza.common.utils.showFancyToast
import com.alijan.laza.databinding.FragmentSignupBinding
import com.alijan.laza.presentation.BaseFragment
import com.alijan.laza.presentation.auth.AuthUiState
import com.alijan.laza.presentation.auth.AuthViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>() {
    private val viewModel by viewModels<AuthViewModel>()
    override fun layoutInflater(): FragmentSignupBinding {
        return FragmentSignupBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        observeData()
        buttonClick()
    }

    private fun observeData() {
        val loadingDialog = requireContext().loadingDialog()

        viewModel.authResult.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            when (it) {
                is AuthUiState.Error -> {
                    requireContext().showFancyToast(
                        "Error: ${it.message}",
                        FancyToast.ERROR
                    )
                }

                AuthUiState.Loading -> {
                    loadingDialog.show()
                }

                AuthUiState.Success -> {
                    lifecycleScope.launch {
                        requireContext().showFancyToast("Success", FancyToast.SUCCESS)
                        delay(2000)
                        findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToSigninFragment())
                    }
                }
            }
        }
    }

    private fun buttonClick() {
        binding.apply {
            buttonSignupCreateAccount.setOnClickListener {
                val email = binding.editTextSignUpEmail.text.toString().trim()
                val password = binding.editTextSignUpPassword.text.toString().trim()
                val username = binding.editTextSignUpUsername.text.toString().trim()
                if (email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty()) {
                    viewModel.signUp(email, password)
                } else {
                    FancyToast.makeText(
                        requireContext(),
                        "Please fill all inputs!",
                        FancyToast.LENGTH_LONG,
                        FancyToast.INFO,
                        false
                    ).show()
                }
            }
        }
    }

}