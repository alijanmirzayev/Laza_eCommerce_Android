package com.alijan.laza.presentation.auth.otp

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.laza.common.OtpKeyListener
import com.alijan.laza.common.OtpTextWatcher
import com.alijan.laza.common.utils.showFancyToast
import com.alijan.laza.databinding.FragmentOtpBinding
import com.alijan.laza.presentation.BaseFragment
import com.alijan.laza.presentation.auth.AuthViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.timer

@AndroidEntryPoint
class OtpFragment : BaseFragment<FragmentOtpBinding>() {
    private val viewModel by viewModels<AuthViewModel>()
    override fun layoutInflater(): FragmentOtpBinding {
        return FragmentOtpBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        setOtpEditTextListeners()
        buttonClick()
        countdown()
    }

    private fun countdown() {
        lifecycleScope.launch {
            var timerSeconds = 30
            for (seconds in timerSeconds downTo 1) {
                timerSeconds -= 1
                binding.textViewOtpCountdown.text = "00:$timerSeconds resend confirmation code"
                delay(1000)
            }
        }
    }

    private fun buttonClick() {
        binding.apply {
            imageViewOtpLeftArrow.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonOtpCreateAccount.setOnClickListener {
                val editTextOtp1 = editTextOtp1.text.toString()
                val editTextOtp2 = editTextOtp2.text.toString()
                val editTextOtp3 = editTextOtp3.text.toString()
                val editTextOtp4 = editTextOtp4.text.toString()
                if (editTextOtp1.isNotEmpty() && editTextOtp2.isNotEmpty() && editTextOtp3.isNotEmpty() && editTextOtp4.isNotEmpty()) {
                    if (editTextOtp1 + editTextOtp2 + editTextOtp3 + editTextOtp4 == "4127") {
                        findNavController().navigate(OtpFragmentDirections.actionOtpFragmentToNewPasswordFragment())
                    } else {
                        requireContext().showFancyToast(
                            "Wrong OTP Code!",
                            FancyToast.ERROR
                        )
                    }
                } else {
                    requireContext().showFancyToast(
                        "Please fill all inputs!",
                        FancyToast.INFO
                    )
                }
            }
        }
    }

    private fun setOtpEditTextListeners() {
        binding.apply {
            editTextOtp1.addTextChangedListener(OtpTextWatcher(editTextOtp1, editTextOtp2))
            editTextOtp2.addTextChangedListener(OtpTextWatcher(editTextOtp2, editTextOtp3))
            editTextOtp3.addTextChangedListener(OtpTextWatcher(editTextOtp3, editTextOtp4))
            editTextOtp4.addTextChangedListener(OtpTextWatcher(editTextOtp4, null))

            editTextOtp1.setOnKeyListener(OtpKeyListener(editTextOtp1, null))
            editTextOtp2.setOnKeyListener(OtpKeyListener(editTextOtp2, editTextOtp1))
            editTextOtp3.setOnKeyListener(OtpKeyListener(editTextOtp3, editTextOtp2))
            editTextOtp4.setOnKeyListener(OtpKeyListener(editTextOtp4, editTextOtp3))
        }
    }

}