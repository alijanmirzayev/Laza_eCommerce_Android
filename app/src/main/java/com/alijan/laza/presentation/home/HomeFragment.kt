package com.alijan.laza.presentation.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.laza.common.utils.loadingDialog
import com.alijan.laza.common.utils.showFancyToast
import com.alijan.laza.databinding.FragmentHomeBinding
import com.alijan.laza.presentation.BaseFragment
import com.alijan.laza.presentation.adapters.BrandAdapter
import com.alijan.laza.presentation.adapters.NewArrivalAdapter
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by viewModels<HomeViewModel>()
    private val brandAdapter = BrandAdapter()
    private val newArrivalAdapter = NewArrivalAdapter()

    override fun layoutInflater(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        observeData()
        setAdapter()
        navigation()
    }

    private fun observeData() {
        val loadingDialog = requireContext().loadingDialog()

        viewModel.brands.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            when (it) {
                HomeUiBrandState.Loading -> loadingDialog.show()
                is HomeUiBrandState.Error -> {
                    requireContext().showFancyToast(
                        "Error: ${it.message}",
                        FancyToast.ERROR
                    )
                }

                is HomeUiBrandState.Success -> {
                    brandAdapter.updateList(it.data)
                }
            }
        }

        viewModel.products.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            when (it) {
                HomeUiProductState.Loading -> loadingDialog.show()
                is HomeUiProductState.Error -> {
                    requireContext().showFancyToast(
                        "Error: ${it.message}",
                        FancyToast.ERROR
                    )
                }

                is HomeUiProductState.Success -> {
                    newArrivalAdapter.updateList(it.data)
                }
            }
        }
    }

    private fun setAdapter() {
        binding.apply {
            rvHomeBrand.adapter = brandAdapter
            rvHomeNewArrival.adapter = newArrivalAdapter
        }
    }

    private fun navigation(){
        newArrivalAdapter.onClick = {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
        }
    }

}