package com.alijan.laza.presentation.auth.signin

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.laza.R
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
                    showToastMessage("Error: ${it.message}", FancyToast.ERROR)
                }

                AuthUiState.Loading -> {
                    loadingDialog.show()
                }

                AuthUiState.Success -> {
                    lifecycleScope.launch {
                        showToastMessage("Success", FancyToast.SUCCESS)
                        delay(1500)
                        viewModel.saveIsRegister(true)
                        findNavController().navigate(R.id.homeFragment)
                    }
                }
            }
        }
    }

    private fun buttonClick() {
        binding.apply {
            buttonSigninCreateAccount.setOnClickListener {
                val email = binding.editTextSignInUsername.text.toString().trim()
                val password = binding.editTextSignInPassword.text.toString().trim()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.signIn(email, password)
                } else {
                    showToastMessage("Please fill all inputs!", FancyToast.INFO)
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

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(
            message,
            type
        )
    }

}