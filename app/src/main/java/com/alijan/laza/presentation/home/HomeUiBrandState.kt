package com.alijan.laza.presentation.home

import com.alijan.laza.data.dto.BrandDTO

sealed class HomeUiBrandState {
    data object Loading : HomeUiBrandState()
    data class Success(val data: List<BrandDTO>) : HomeUiBrandState()
    data class Error(val message: String) : HomeUiBrandState()
}