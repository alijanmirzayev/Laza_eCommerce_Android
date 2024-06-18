package com.alijan.laza.presentation.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.data.dto.local.BasketLocalDTO
import com.alijan.laza.domain.usecase.AddBasketToLocalUseCase
import com.alijan.laza.domain.usecase.DeleteAllBasketToLocalUseCase
import com.alijan.laza.domain.usecase.DeleteBasketToLocalUseCase
import com.alijan.laza.domain.usecase.GetAddressByLocalUseCase
import com.alijan.laza.domain.usecase.GetAllBasketByLocalUseCase
import com.alijan.laza.domain.usecase.GetCardInformationToLocalUseCase
import com.alijan.laza.presentation.address.AddressUiState
import com.alijan.laza.presentation.payment.PaymentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getAllBasketByLocalUseCase: GetAllBasketByLocalUseCase,
    private val deleteBasketToLocalUseCase: DeleteBasketToLocalUseCase,
    private val addBasketToLocalUseCase: AddBasketToLocalUseCase,
    private val getCardInformationToLocalUseCase: GetCardInformationToLocalUseCase,
    private val getAddressByLocalUseCase: GetAddressByLocalUseCase,
    private val deleteAllBasketToLocalUseCase: DeleteAllBasketToLocalUseCase
) :
    ViewModel() {

    private var _basket = MutableLiveData<BasketUiState>()
    val brands: LiveData<BasketUiState> get() = _basket

    private var _address = MutableLiveData<AddressUiState>()
    val address: LiveData<AddressUiState> get() = _address

    private var _card = MutableLiveData<PaymentUiState>()
    val card: LiveData<PaymentUiState> get() = _card

    init {
        getAllBasketByLocal()
        getCardInformation()
        getAddressByLocal()
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

    fun getCardInformation() {
        viewModelScope.launch {
            _card.value = PaymentUiState.Loading
            try {
                when (val resp = getCardInformationToLocalUseCase.execute()) {
                    is NetworkResponse.Error -> _card.value =
                        resp.message?.let { PaymentUiState.Error(it) }

                    is NetworkResponse.Success -> _card.value =
                        PaymentUiState.Success(resp.data!!)
                }
            } catch (e: Exception) {
                _card.value = PaymentUiState.Error(e.localizedMessage)
            }
        }
    }

    fun getAddressByLocal() {
        viewModelScope.launch {
            _address.value = AddressUiState.Loading
            try {
                when (val resp = getAddressByLocalUseCase.execute()) {
                    is NetworkResponse.Error -> _address.value =
                        resp.message?.let { AddressUiState.Error(it) }

                    is NetworkResponse.Success -> _address.value =
                        AddressUiState.Success(resp.data!!)
                }
            } catch (e: Exception) {
                _address.value = AddressUiState.Error(e.localizedMessage)
            }
        }
    }

    fun deleteBasketByLocal(deletedItem: BasketLocalDTO) {
        viewModelScope.launch {
            deleteBasketToLocalUseCase.execute(deletedItem)
        }
    }

    fun deleteAllBasketByLocal() {
        viewModelScope.launch {
            deleteAllBasketToLocalUseCase.execute()
        }
    }

    fun updateBasketByLocal(updatedItem: BasketLocalDTO) {
        viewModelScope.launch {
            addBasketToLocalUseCase.execute(updatedItem)
        }
    }

}