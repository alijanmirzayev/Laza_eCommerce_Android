package com.alijan.laza.domain.repository

import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.data.dto.BrandDTO
import com.alijan.laza.data.dto.ProductDTO
import com.alijan.laza.data.dto.local.AddressLocalDTO
import com.alijan.laza.data.dto.local.BasketLocalDTO

interface ProductRepository {

    suspend fun getAllBrands(): NetworkResponse<List<BrandDTO>>
    suspend fun getNewArrivalProduct(): NetworkResponse<List<ProductDTO>>
    suspend fun getProductById(id: String): NetworkResponse<ProductDTO>
    suspend fun getAllBasketByLocal(): NetworkResponse<List<BasketLocalDTO>>
    suspend fun addBasketToLocal(item: BasketLocalDTO): NetworkResponse<Unit>
    suspend fun deleteBasketToLocal(item: BasketLocalDTO): NetworkResponse<Unit>
    suspend fun updateAddressInformationToLocal(item: AddressLocalDTO): NetworkResponse<Unit>
    suspend fun getAddressInformationToLocal(): NetworkResponse<List<AddressLocalDTO>>

}