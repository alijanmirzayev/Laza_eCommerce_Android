package com.alijan.laza.presentation.address

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.laza.common.utils.loadingDialog
import com.alijan.laza.common.utils.showFancyToast
import com.alijan.laza.data.dto.local.AddressLocalDTO
import com.alijan.laza.databinding.FragmentAddressBinding
import com.alijan.laza.presentation.BaseFragment
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressFragment : BaseFragment<FragmentAddressBinding>() {
    private val viewModel by viewModels<AddressViewModel>()
    override fun layoutInflater(): FragmentAddressBinding {
        return FragmentAddressBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        observeData()
        navigation()
        saveAddress()
    }

    private fun observeData() {
        val loadingDialog = requireContext().loadingDialog()
        viewModel.address.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            when (it) {
                is AddressUiState.Error -> showToastMessage(
                    "Error: ${it.message}",
                    FancyToast.ERROR
                )

                AddressUiState.Loading -> loadingDialog.show()
                is AddressUiState.Success -> {
                    if (it.data.isNotEmpty()) {
                        binding.apply {
                            editTextAddressName.setText(it.data[0].addressName)
                            editTextAddressCountry.setText(it.data[0].addressCountry)
                            editTextAddressCity.setText(it.data[0].addressCity)
                            editTextAddressPhoneNumber.setText(it.data[0].addressPhoneNumber)
                            editTextAddressAddress.setText(it.data[0].addressFullAddress)
                        }
                    } else showToastMessage("Please fill all input", FancyToast.INFO)
                }
            }
        }
    }

    private fun navigation() {
        binding.apply {
            imageViewAddressLeft.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun saveAddress() {
        binding.apply {
            buttonAddressSave.setOnClickListener {
                val addressName = editTextAddressName.text.toString().trim()
                val addressCountry = editTextAddressCountry.text.toString().trim()
                val addressCity = editTextAddressCity.text.toString().trim()
                val addressPhoneNumber = editTextAddressPhoneNumber.text.toString().trim()
                val addressFullAddress = editTextAddressAddress.text.toString().trim()
                if (addressName.isNotEmpty()
                    && addressCountry.isNotEmpty()
                    && addressCity.isNotEmpty()
                    && addressPhoneNumber.isNotEmpty()
                    && addressFullAddress.isNotEmpty()
                ) {
                    val item = AddressLocalDTO(
                        "1",
                        addressName,
                        addressCountry,
                        addressCity,
                        addressPhoneNumber,
                        addressFullAddress
                    )
                    viewModel.updateAddressByLocal(item)
                    showToastMessage("Updated",FancyToast.SUCCESS)
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