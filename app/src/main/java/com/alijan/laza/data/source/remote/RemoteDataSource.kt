package com.alijan.laza.data.source.remote

import com.alijan.laza.data.source.remote.dto.BrandDTO
import com.alijan.laza.data.source.remote.dto.ProductDTO
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getAllBrands(): Response<List<BrandDTO>>
    suspend fun getNewArrivalProduct(): Response<List<ProductDTO>>

}