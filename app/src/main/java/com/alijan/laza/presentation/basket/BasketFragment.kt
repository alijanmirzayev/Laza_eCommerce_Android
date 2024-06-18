package com.alijan.laza.presentation.basket

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.laza.common.utils.loadingDialog
import com.alijan.laza.common.utils.showFancyToast
import com.alijan.laza.databinding.FragmentBasketBinding
import com.alijan.laza.presentation.BaseFragment
import com.alijan.laza.presentation.adapters.BasketAdapter
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding>() {
    private val viewModel by viewModels<BasketViewModel>()
    private val basketAdapter = BasketAdapter()
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
                    basketAdapter.updateList(it.data)
                }
            }
        }
    }

    private fun navigation(){
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
        }
    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(
            message,
            type
        )
    }

}