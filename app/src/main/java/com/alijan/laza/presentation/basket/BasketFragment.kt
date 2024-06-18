package com.alijan.laza.presentation.basket

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.laza.common.utils.loadingDialog
import com.alijan.laza.common.utils.showFancyToast
import com.alijan.laza.databinding.FragmentBasketBinding
import com.alijan.laza.presentation.BaseFragment
import com.alijan.laza.presentation.adapters.BasketAdapter
import com.alijan.laza.presentation.address.AddressUiState
import com.alijan.laza.presentation.payment.PaymentUiState
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding>() {
    private val viewModel by viewModels<BasketViewModel>()
    private val basketAdapter = BasketAdapter()
    private var isBasketFree = true
    override fun layoutInflater(): FragmentBasketBinding {
        return FragmentBasketBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        setAdapter()
        observeData()
        navigation()
    }

    private fun setAdapter() {
        binding.rvBasket.adapter = basketAdapter
        basketAdapter.apply {
            onClickDelete = {
                viewModel.deleteBasketByLocal(it)
            }
            onClickIncrement = {
                viewModel.updateBasketByLocal(it)
            }
            onClickDecrement = {
                viewModel.updateBasketByLocal(it)
            }
            onClickCalculation = {
                var totalCost: Double? = 0.00
                it.forEach { it1 ->
                    totalCost =
                        it1.productPrice?.toDouble()?.let { it2 -> it1.productCount?.times(it2) }
                }
                if (totalCost == 0.00) {
                    binding.apply {
                        textViewBasketShippingCostCost.text = "$0.00"
                        textViewBasketSubtotalCost.text = "$0.00"
                        textViewBasketTotalCost.text = "$0.00"
                    }
                } else {
                    binding.apply {
                        textViewBasketShippingCostCost.text = "$12.0"
                        textViewBasketSubtotalCost.text = "$${totalCost.toString()}"
                        textViewBasketTotalCost.text = "$${(totalCost?.plus(12.00)).toString()}"
                    }
                }
            }
        }
    }

    private fun observeData() {
        val loadingDialog = requireContext().loadingDialog()
        viewModel.brands.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            when (it) {
                is BasketUiState.Error -> showToastMessage("Error: ${it.message}", FancyToast.ERROR)
                BasketUiState.Loading -> loadingDialog.show()
                is BasketUiState.Success -> {
                    if (it.data.isNotEmpty()) {
                        basketAdapter.updateList(it.data)
                        isBasketFree = false
                    }
                }
            }
        }

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
                            textViewBasketAddress.setText(it.data[0].addressFullAddress)
                            textViewBasketCity.setText(it.data[0].addressCity)
                        }
                    }
                }
            }
        }

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
                        binding.textViewBasketCardInfo.setText(
                            "**** ${
                                it.data[0].cardNumber.substring(
                                    12
                                )
                            }"
                        )
                    }
                }
            }
        }
    }

    private fun navigation() {
        binding.apply {
            imageViewBasketLeft.setOnClickListener {
                findNavController().popBackStack()
            }
            imageViewBasketDeliveryRight.setOnClickListener {
                findNavController().navigate(BasketFragmentDirections.actionBasketFragmentToAddressFragment())
            }
            imageViewBasketPaymentRight.setOnClickListener {
                findNavController().navigate(BasketFragmentDirections.actionBasketFragmentToPaymentFragment())
            }
            buttonBasketCheckout.setOnClickListener {
                if(!isBasketFree) {
                    viewModel.deleteAllBasketByLocal()
                    findNavController().navigate(BasketFragmentDirections.actionBasketFragmentToOrderConfirmationFragment())
                }
                else showToastMessage("Your basket is free!",FancyToast.WARNING)
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