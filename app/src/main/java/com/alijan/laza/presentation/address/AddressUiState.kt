package com.alijan.laza.presentation.address

import com.alijan.laza.data.dto.local.AddressLocalDTO

sealed class AddressUiState {
    data object Loading : AddressUiState()
    data class Success(val data: List<AddressLocalDTO>) : AddressUiState()
    data class Error(val message: String) : AddressUiState()
}