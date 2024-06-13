package com.alijan.laza.presentation.auth.newpassword

import androidx.navigation.fragment.findNavController
import com.alijan.laza.databinding.FragmentNewpasswordBinding
import com.alijan.laza.presentation.BaseFragment

class NewPasswordFragment : BaseFragment<FragmentNewpasswordBinding>() {
    override fun layoutInflater(): FragmentNewpasswordBinding {
        return FragmentNewpasswordBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        buttonClick()
    }

    private fun buttonClick(){
        binding.apply {
            imageViewNewPasswordLeftArrow.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}