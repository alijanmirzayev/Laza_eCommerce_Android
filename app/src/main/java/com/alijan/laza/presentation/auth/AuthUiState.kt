package com.alijan.laza.presentation.auth

sealed class AuthUiState {
    data object Loading : AuthUiState()
    data object Success : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}