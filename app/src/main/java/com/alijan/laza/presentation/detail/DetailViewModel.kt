package com.alijan.laza.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.common.toBasketLocalDTO
import com.alijan.laza.data.dto.ProductDTO
import com.alijan.laza.domain.usecase.AddBasketToLocalUseCase
import com.alijan.laza.domain.usecase.GetProductByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addBasketToLocalUseCase: AddBasketToLocalUseCase
) :
    ViewModel() {

    private var _products = MutableLiveData<DetailUiProductState>()
    val products: LiveData<DetailUiProductState> get() = _products

    fun getProductById(id: String) {
        viewModelScope.launch {
            _products.value = DetailUiProductState.Loading
            try {
                when (val resp = getProductByIdUseCase.execute(id)) {
                    is NetworkResponse.Error -> _products.value =
                        DetailUiProductState.Error(resp.message.toString())

                    is NetworkResponse.Success -> _products.value =
                        DetailUiProductState.Success(resp.data!!)
                }
            } catch (e: Exception) {
                _products.value = DetailUiProductState.Error(e.localizedMessage)
            }
        }
    }

    fun addBasketToLocal(productDTO: ProductDTO, size: String, count: Int) {
        viewModelScope.launch {
            addBasketToLocalUseCase.execute(productDTO.toBasketLocalDTO(size, count))
        }
    }

}