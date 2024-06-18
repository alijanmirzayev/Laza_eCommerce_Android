package com.alijan.laza.presentation.payment

import com.alijan.laza.data.dto.local.CardLocalDTO

sealed class PaymentUiState {
    data object Loading : PaymentUiState()
    data class Success(val data: List<CardLocalDTO>) : PaymentUiState()
    data class Error(val message: String) : PaymentUiState()
}