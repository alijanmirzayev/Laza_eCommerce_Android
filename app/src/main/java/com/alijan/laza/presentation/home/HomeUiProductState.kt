package com.alijan.laza.presentation.home

import com.alijan.laza.data.dto.ProductDTO

sealed class HomeUiProductState {
    data object Loading : HomeUiProductState()
    data class Success(val data: List<ProductDTO>) : HomeUiProductState()
    data class Error(val message: String) : HomeUiProductState()
}