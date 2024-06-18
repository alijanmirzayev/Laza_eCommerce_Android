package com.alijan.laza.presentation.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.data.dto.local.BasketLocalDTO
import com.alijan.laza.domain.usecase.AddBasketToLocalUseCase
import com.alijan.laza.domain.usecase.DeleteBasketToLocalUseCase
import com.alijan.laza.domain.usecase.GetAllBasketByLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getAllBasketByLocalUseCase: GetAllBasketByLocalUseCase,
    private val deleteBasketToLocalUseCase: DeleteBasketToLocalUseCase,
    private val addBasketToLocalUseCase: AddBasketToLocalUseCase
) :
    ViewModel() {

    private var _basket = MutableLiveData<BasketUiState>()
    val brands: LiveData<BasketUiState> get() = _basket

    init {
        getAllBasketByLocal()
    }

    private fun getAllBasketByLocal() {
        viewModelScope.launch {
            _basket.value = BasketUiState.Loading
            try {
                when (val resp = getAllBasketByLocalUseCase.execute()) {
                    is NetworkResponse.Error -> _basket.value =
                        resp.message?.let { BasketUiState.Error(it) }

                    is NetworkResponse.Success -> _basket.value =
                        BasketUiState.Success(resp.data!!)
                }
            } catch (e: Exception) {
                _basket.value = BasketUiState.Error(e.localizedMessage)
            }
        }
    }

    fun deleteBasketByLocal(deletedItem: BasketLocalDTO) {
        viewModelScope.launch {
            deleteBasketToLocalUseCase.execute(deletedItem)
        }
    }

    fun updateBasketByLocal(updatedItem: BasketLocalDTO) {
        viewModelScope.launch {
            addBasketToLocalUseCase.execute(updatedItem)
        }
    }

}