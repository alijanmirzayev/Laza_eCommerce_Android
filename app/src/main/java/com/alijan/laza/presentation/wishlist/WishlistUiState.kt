package com.alijan.laza.presentation.wishlist

import com.alijan.laza.data.dto.local.WishlistLocalDTO

sealed class WishlistUiState {
    data object Loading : WishlistUiState()
    data class Success(val data: List<WishlistLocalDTO>) : WishlistUiState()
    data class Error(val message: String) : WishlistUiState()
}