package com.alijan.laza.presentation.payment

import com.alijan.laza.databinding.FragmentPaymentBinding
import com.alijan.laza.presentation.BaseFragment

class PaymentFragment : BaseFragment<FragmentPaymentBinding>() {
    override fun layoutInflater(): FragmentPaymentBinding {
        return FragmentPaymentBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
    }

}