package com.alijan.laza.data.source.remote

import com.alijan.laza.data.dto.BrandDTO
import com.alijan.laza.data.dto.ProductDTO
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getAllBrands(): Response<List<BrandDTO>>
    suspend fun getNewArrivalProduct(): Response<List<ProductDTO>>
    suspend fun getProductById(id: String): Response<ProductDTO>

}