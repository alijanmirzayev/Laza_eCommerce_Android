package com.alijan.laza.presentation.orderconfirmation

import androidx.navigation.fragment.findNavController
import com.alijan.laza.databinding.FragmentOrderConfirmationBinding
import com.alijan.laza.presentation.BaseFragment

class OrderConfirmationFragment : BaseFragment<FragmentOrderConfirmationBinding>() {
    override fun layoutInflater(): FragmentOrderConfirmationBinding {
        return FragmentOrderConfirmationBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        navigation()
    }

    private fun navigation(){
        binding.apply {
            imageViewOrderLeft.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonOrderContinueShopping.setOnClickListener {
                findNavController().navigate(OrderConfirmationFragmentDirections.actionOrderConfirmationFragmentToHomeFragment())
            }
        }
    }

}