package com.alijan.laza.presentation.auth.signin

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.laza.common.utils.loadingDialog
import com.alijan.laza.common.utils.showFancyToast
import com.alijan.laza.databinding.FragmentSigninBinding
import com.alijan.laza.presentation.BaseFragment
import com.alijan.laza.presentation.auth.AuthUiState
import com.alijan.laza.presentation.auth.AuthViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@AndroidEntryPoint
class SigninFragment : BaseFragment<FragmentSigninBinding>() {
    private val viewModel by viewModels<AuthViewModel>()
    override fun layoutInflater(): FragmentSigninBinding {
        return FragmentSigninBinding.inflate(layoutInflater)
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
                        // Home navigate ele
                    }
                }
            }
        }
    }

    private fun buttonClick(){
        binding.apply {
            buttonSigninCreateAccount.setOnClickListener {
                val email = binding.editTextSignInUsername.text.toString().trim()
                val password = binding.editTextSignInPassword.text.toString().trim()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.signIn(email, password)
                } else {
                    requireContext().showFancyToast(
                        "Please fill all inputs!",
                        FancyToast.INFO
                    )
                }
            }

            textViewSignInForgotPassword.setOnClickListener {
                findNavController().navigate(SigninFragmentDirections.actionSigninFragmentToForgotPasswordFragment())
            }

            imageViewSigninLeftArrow.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}