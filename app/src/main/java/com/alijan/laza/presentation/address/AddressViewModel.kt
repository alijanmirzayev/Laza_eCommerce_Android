package com.alijan.laza.presentation.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.data.dto.local.AddressLocalDTO
import com.alijan.laza.domain.usecase.GetAddressByLocalUseCase
import com.alijan.laza.domain.usecase.UpdateAddressByLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val updateAddressByLocalUseCase: UpdateAddressByLocalUseCase,
    private val getAddressByLocalUseCase: GetAddressByLocalUseCase
) : ViewModel() {

    private var _address = MutableLiveData<AddressUiState>()
    val address: LiveData<AddressUiState> get() = _address

    init {
        getAddressByLocal()
    }

    private fun getAddressByLocal() {
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

    fun updateAddressByLocal(item: AddressLocalDTO) {
        viewModelScope.launch {
            updateAddressByLocalUseCase.execute(item)
        }
    }

}