package com.alijan.laza.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.common.toWishlistLocalDTO
import com.alijan.laza.data.dto.ProductDTO
import com.alijan.laza.data.dto.local.WishlistLocalDTO
import com.alijan.laza.domain.usecase.AddItemWishlistToLocalUseCase
import com.alijan.laza.domain.usecase.DeleteItemWishlistToLocalUseCase
import com.alijan.laza.domain.usecase.GetAllBrandsUseCase
import com.alijan.laza.domain.usecase.GetAllWishlistByLocalUseCase
import com.alijan.laza.domain.usecase.GetNewArrivalProductsUseCase
import com.alijan.laza.presentation.address.AddressUiState
import com.alijan.laza.presentation.wishlist.WishlistUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllBrandsUseCase: GetAllBrandsUseCase,
    private val getAllNewArrivalProductsUseCase: GetNewArrivalProductsUseCase,
    private val getAllWishlistByLocalUseCase: GetAllWishlistByLocalUseCase,
    private val addItemWishlistToLocalUseCase: AddItemWishlistToLocalUseCase,
    private val deleteItemWishlistToLocalUseCase: DeleteItemWishlistToLocalUseCase,
) : ViewModel() {

    private var _brands = MutableLiveData<HomeUiBrandState>()
    val brands: LiveData<HomeUiBrandState> get() = _brands

    private var _products = MutableLiveData<HomeUiProductState>()
    val products: LiveData<HomeUiProductState> get() = _products

    private var _wishlist = MutableLiveData<WishlistUiState>()
    val wishlist: LiveData<WishlistUiState> get() = _wishlist

    init {
        getAllBrands()
        getAllNewArrivalProducts()
        getAllWishlistByLocal()
    }

    private fun getAllBrands() {
        viewModelScope.launch {
            _brands.value = HomeUiBrandState.Loading
            try {
                when (val resp = getAllBrandsUseCase.execute()) {
                    is NetworkResponse.Error -> _brands.value =
                        HomeUiBrandState.Error(resp.message.toString())

                    is NetworkResponse.Success -> _brands.value =
                        HomeUiBrandState.Success(resp.data!!)
                }
            } catch (e: Exception) {
                _brands.value = HomeUiBrandState.Error(e.localizedMessage)
            }
        }
    }

    private fun getAllNewArrivalProducts() {
        viewModelScope.launch {
            _products.value = HomeUiProductState.Loading
            try {
                when (val resp = getAllNewArrivalProductsUseCase.execute()) {
                    is NetworkResponse.Error -> _products.value =
                        HomeUiProductState.Error(resp.message.toString())

                    is NetworkResponse.Success -> _products.value =
                        HomeUiProductState.Success(resp.data!!)
                }
            } catch (e: Exception) {
                _products.value = HomeUiProductState.Error(e.localizedMessage)
            }
        }
    }

    fun getAllWishlistByLocal(){
        viewModelScope.launch {
            _wishlist.value = WishlistUiState.Loading
            try {
                when (val resp = getAllWishlistByLocalUseCase.execute()) {
                    is NetworkResponse.Error -> _wishlist.value =
                        resp.message?.let { WishlistUiState.Error(it) }

                    is NetworkResponse.Success -> _wishlist.value =
                        WishlistUiState.Success(resp.data!!)
                }
            } catch (e: Exception) {
                _wishlist.value = WishlistUiState.Error(e.localizedMessage)
            }
        }
    }

    fun addItemWishlistToLocal(item: ProductDTO){
        viewModelScope.launch {
            addItemWishlistToLocalUseCase.execute(item.toWishlistLocalDTO())
        }
    }

    fun deleteItemWishlistToLocal(item: ProductDTO){
        viewModelScope.launch {
            deleteItemWishlistToLocalUseCase.execute(item.toWishlistLocalDTO())
        }
    }

}