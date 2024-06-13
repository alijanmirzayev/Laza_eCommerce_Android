package com.alijan.laza.data.source.remote

import com.alijan.laza.data.source.remote.dto.BrandDTO
import com.alijan.laza.data.source.remote.dto.ProductDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("brands")
    suspend fun getAllBrands(): Response<List<BrandDTO>>

    @GET("products")
    suspend fun getNewArrivalProducts(): Response<List<ProductDTO>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): Response<ProductDTO>

}