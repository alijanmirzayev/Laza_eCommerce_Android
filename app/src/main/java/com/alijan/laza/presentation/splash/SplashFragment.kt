package com.alijan.laza.presentation.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.laza.R
import com.alijan.laza.common.utils.showFancyToast
import com.alijan.laza.databinding.FragmentSplashBinding
import com.alijan.laza.presentation.BaseFragment
import com.alijan.laza.presentation.auth.AuthUiState
import com.alijan.laza.presentation.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    private val viewModel by viewModels<AuthViewModel>()

    override fun layoutInflater(): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        observeData()
        viewModel.getIsRegister()
    }

    private fun observeData() {
        viewModel.authResult.observe(viewLifecycleOwner) {
            when (it) {
                AuthUiState.Loading -> {}
                is AuthUiState.Error -> {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToChooseGenderFragment())
                }

                AuthUiState.Success -> {
                    findNavController().navigate(R.id.homeFragment)
                }
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