package com.alijan.laza.presentation.auth.forgotpassword

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.laza.common.utils.loadingDialog
import com.alijan.laza.common.utils.showFancyToast
import com.alijan.laza.databinding.FragmentForgotPasswordBinding
import com.alijan.laza.presentation.BaseFragment
import com.alijan.laza.presentation.auth.AuthUiState
import com.alijan.laza.presentation.auth.AuthViewModel
import com.alijan.laza.presentation.auth.signin.SigninFragmentDirections
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {
    private val viewModel by viewModels<AuthViewModel>()
    override fun layoutInflater(): FragmentForgotPasswordBinding {
        return FragmentForgotPasswordBinding.inflate(layoutInflater)
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
                    findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToOtpFragment())
                }

                AuthUiState.Loading -> {
                    loadingDialog.show()
                }

                AuthUiState.Success -> {
                    lifecycleScope.launch {
                        requireContext().showFancyToast("Success", FancyToast.SUCCESS)
                        delay(2000)
                        findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToOtpFragment())
                    }
                }
            }
        }
    }

    private fun buttonClick(){
        binding.apply {
            buttonForgotPasswordCreateAccount.setOnClickListener {
                val email = binding.editTextForgotPasswordEmail.text.toString().trim()
                if (email.isNotEmpty()) {
                    viewModel.resetPassword(email)
                } else {
                    requireContext().showFancyToast(
                        "Please fill input!",
                        FancyToast.INFO
                    )
                }
            }

            imageViewForgotPasswordLeftArrow.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}