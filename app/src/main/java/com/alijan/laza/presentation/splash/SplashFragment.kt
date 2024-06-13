package com.alijan.laza.presentation.splash

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.laza.databinding.FragmentSplashBinding
import com.alijan.laza.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun layoutInflater(): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToChooseGenderFragment())
        }
    }
}