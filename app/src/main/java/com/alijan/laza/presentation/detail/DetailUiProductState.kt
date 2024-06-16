package com.alijan.laza.presentation.detail

import com.alijan.laza.data.dto.ProductDTO

sealed class DetailUiProductState {
    data object Loading : DetailUiProductState()
    data class Success(val data: ProductDTO) : DetailUiProductState()
    data class Error(val message: String) : DetailUiProductState()
}