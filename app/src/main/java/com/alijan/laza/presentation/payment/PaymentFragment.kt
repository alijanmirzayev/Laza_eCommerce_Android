package com.alijan.laza.presentation.payment

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.laza.common.utils.loadingDialog
import com.alijan.laza.common.utils.showFancyToast
import com.alijan.laza.data.dto.local.CardLocalDTO
import com.alijan.laza.databinding.FragmentPaymentBinding
import com.alijan.laza.presentation.BaseFragment
import com.alijan.laza.presentation.basket.BasketUiState
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>() {
    private val viewModel by viewModels<PaymentViewModel>()
    override fun layoutInflater(): FragmentPaymentBinding {
        return FragmentPaymentBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        observeData()
        navigation()
        saveCard()
        textWatcherCard()
    }

    private fun observeData() {
        val loadingDialog = requireContext().loadingDialog()
        viewModel.card.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            when (it) {
                is PaymentUiState.Error -> showToastMessage(
                    "Error: ${it.message}",
                    FancyToast.ERROR
                )

                PaymentUiState.Loading -> loadingDialog.show()
                is PaymentUiState.Success -> {
                    if (it.data.isNotEmpty()) {
                        binding.apply {
                            editTextPaymentCardOwner.setText(it.data[0].cardOwner)
                            editTextPaymentCardNumber.setText(it.data[0].cardNumber)
                            editTextPaymentExp.setText(it.data[0].cardExp)
                            editTextPaymentCvv.setText(it.data[0].cardCvv)
                        }
                    }
                }
            }
        }
    }

    private fun navigation() {
        binding.imageViewPaymentLeft.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun saveCard() {
        binding.apply {
            buttonPaymentSave.setOnClickListener {
                val cardOwner = editTextPaymentCardOwner.text.toString().trim()
                val cardNumber = editTextPaymentCardNumber.text.toString().trim()
                val cardExp = editTextPaymentExp.text.toString().trim()
                val cardCvv = editTextPaymentCvv.text.toString().trim()

                if (cardOwner.isNotEmpty() && cardNumber.isNotEmpty() && cardExp.isNotEmpty() && cardCvv.isNotEmpty()) {
                    val item = CardLocalDTO(
                        "1", cardOwner, cardNumber, cardExp, cardCvv
                    )
                    viewModel.updateCardInformationByLocal(item)
                    showToastMessage("Update card", FancyToast.SUCCESS)
                } else showToastMessage("Please fill all input", FancyToast.INFO)
            }
        }
    }

    private fun textWatcherCard() {
        setupTextWatcher(binding.editTextPaymentCardOwner, binding.textViewPaymentCardCardOwner,"cardOwner")
        setupTextWatcher(binding.editTextPaymentCardNumber, binding.textViewPaymentCardCardNumber,"cardNumber")
    }

    private fun setupTextWatcher(editText: EditText, textView: TextView, type: String) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (type == "cardOwner"){
                    textView.setText(s.toString().uppercase())
                } else {
                    val result = formatCardNumber(s.toString().trim())
                    textView.setText(result)
                }
            }
        })
    }

    private fun formatCardNumber(cardNumber: String): String {
        val sb = StringBuilder()
        for (i in cardNumber.indices) {
            if (i > 0 && i % 4 == 0) {
                sb.append(" ")
            }
            sb.append(cardNumber[i])
        }
        return sb.toString()
    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(
            message,
            type
        )
    }

}