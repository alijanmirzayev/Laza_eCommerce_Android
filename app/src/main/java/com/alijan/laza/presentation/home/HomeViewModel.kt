package com.alijan.laza.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.domain.usecase.GetAllBrandsUseCase
import com.alijan.laza.domain.usecase.GetNewArrivalProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllBrandsUseCase: GetAllBrandsUseCase,
    private val getAllNewArrivalProductsUseCase: GetNewArrivalProductsUseCase
) :
    ViewModel() {

    private var _brands = MutableLiveData<HomeUiBrandState>()
    val brands: LiveData<HomeUiBrandState> get() = _brands

    private var _products = MutableLiveData<HomeUiProductState>()
    val products: LiveData<HomeUiProductState> get() = _products

    init {
        getAllBrands()
        getAllNewArrivalProducts()
    }

    private fun getAllBrands() {
        viewModelScope.launch {
            _brands.value = HomeUiBrandState.Loading
            try {
                when (val resp = getAllBrandsUseCase.execute()) {
                    is NetworkResponse.Error -> _brands.value =
                        HomeUiBrandState.Error(resp.message.toString())

                    is NetworkResponse.Success -> _brands.value =
                        HomeUiBrandState.Success(resp.data!!)
                }
            } catch (e: Exception) {
                _brands.value = HomeUiBrandState.Error(e.localizedMessage)
            }
        }
    }

    private fun getAllNewArrivalProducts() {
        viewModelScope.launch {
            _products.value = HomeUiProductState.Loading
            try {
                when (val resp = getAllNewArrivalProductsUseCase.execute()) {
                    is NetworkResponse.Error -> _products.value =
                        HomeUiProductState.Error(resp.message.toString())

                    is NetworkResponse.Success -> _products.value =
                        HomeUiProductState.Success(resp.data!!)
                }
            } catch (e: Exception) {
                _products.value = HomeUiProductState.Error(e.localizedMessage)
            }
        }
    }

}