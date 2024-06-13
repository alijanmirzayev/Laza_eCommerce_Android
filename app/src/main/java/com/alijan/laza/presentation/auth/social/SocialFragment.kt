package com.alijan.laza.presentation.auth.social

import androidx.navigation.fragment.findNavController
import com.alijan.laza.databinding.FragmentSocialBinding
import com.alijan.laza.presentation.BaseFragment

class SocialFragment : BaseFragment<FragmentSocialBinding>() {
    override fun layoutInflater(): FragmentSocialBinding {
        return FragmentSocialBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        buttonClick()
    }

    private fun buttonClick() {
        binding.apply {
            buttonSocialCreateAccount.setOnClickListener {
                findNavController().navigate(SocialFragmentDirections.actionSocialFragmentToSignupFragment())
            }
            textViewSocialSignIn.setOnClickListener {
                findNavController().navigate(SocialFragmentDirections.actionSocialFragmentToSigninFragment())
            }
            imageViewSocialLeftArrow.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}