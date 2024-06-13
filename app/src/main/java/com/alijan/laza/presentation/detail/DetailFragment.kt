package com.alijan.laza.presentation.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alijan.laza.common.utils.loadingDialog
import com.alijan.laza.common.utils.showFancyToast
import com.alijan.laza.databinding.FragmentDetailBinding
import com.alijan.laza.presentation.BaseFragment
import com.alijan.laza.presentation.adapters.ImageAdapter
import com.alijan.laza.presentation.adapters.SizeAdapter
import com.alijan.laza.presentation.home.HomeUiProductState
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val viewModel by viewModels<DetailViewModel>()
    private val imageAdapter = ImageAdapter()
    private val sizeAdapter = SizeAdapter()
    private val args: DetailFragmentArgs by navArgs()
    override fun layoutInflater(): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        requestApi()
        observeData()
        setAdapter()
        buttonClick()
    }

    private fun requestApi(){
        viewModel.getProductById(args.id)
    }

    private fun observeData(){
        val loadingDialog = requireContext().loadingDialog()

        viewModel.products.observe(viewLifecycleOwner) {
            loadingDialog.dismiss()
            when (it) {
                DetailUiProductState.Loading -> loadingDialog.show()
                is DetailUiProductState.Error -> {
                    requireContext().showFancyToast(
                        "Error: ${it.message}",
                        FancyToast.ERROR
                    )
                }

                is DetailUiProductState.Success -> {
                    binding.item = it.data
                    sizeAdapter.updateList(it.data.productSize!!)
                    imageAdapter.updateList(it.data.productImage!!)
                }
            }
        }
    }

    private fun setAdapter() {
        binding.apply {
            rvDetailSize.adapter = sizeAdapter
            rvDetailImage.adapter = imageAdapter
        }
    }

    private fun buttonClick(){
        binding.apply {
            imageViewDetailLeft.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}