package com.alijan.laza.domain.repository

import com.alijan.laza.common.NetworkResponse
import com.alijan.laza.data.source.remote.dto.BrandDTO
import com.alijan.laza.data.source.remote.dto.ProductDTO

interface ProductRepository {

    suspend fun getAllBrands(): NetworkResponse<List<BrandDTO>>
    suspend fun getNewArrivalProduct(): NetworkResponse<List<ProductDTO>>
    suspend fun getProductById(id: String): NetworkResponse<ProductDTO>


}