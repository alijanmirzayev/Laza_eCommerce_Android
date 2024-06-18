package com.alijan.laza.presentation.basket

import com.alijan.laza.data.dto.local.BasketLocalDTO

sealed class BasketUiState {
    data object Loading : BasketUiState()
    data class Success(val data: List<BasketLocalDTO>) : BasketUiState()
    data class Error(val message: String) : BasketUiState()
}