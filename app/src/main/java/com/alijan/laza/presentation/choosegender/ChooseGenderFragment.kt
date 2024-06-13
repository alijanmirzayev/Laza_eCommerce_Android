package com.alijan.laza.presentation.choosegender

import androidx.navigation.fragment.findNavController
import com.alijan.laza.databinding.FragmentChooseGenderBinding
import com.alijan.laza.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseGenderFragment : BaseFragment<FragmentChooseGenderBinding>() {
    override fun layoutInflater(): FragmentChooseGenderBinding {
        return FragmentChooseGenderBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        binding.buttonChooseGenderSkip.setOnClickListener {
            findNavController().navigate(ChooseGenderFragmentDirections.actionChooseGenderFragmentToAuthNav())
        }
    }

}